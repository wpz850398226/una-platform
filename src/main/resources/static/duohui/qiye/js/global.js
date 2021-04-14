$(function () {
	_68.init();
	var gt = $('#gotop.gotop').click(function () { $('html,body').animate({ scrollTop: 0 }, 300); return false; });
	$(window)
		.resize(function () {
			_68.center($('.floatbox'));
			_68.center($('#upMsg'));
		})
		.scroll(function () {
			if (_68.toplock != null) $(window).scrollTop(_68.toplock);
			if (gt.length > 0) {
				if ($(window).scrollTop() > 50) gt.fadeIn(300);
				else gt.fadeOut(200);
			}
		})
	;
	$(document)
		.click(function () { $('select[size]').click(); })
		.keydown(function (e) {
			if (e.keyCode == 27) _68.fb.hide($('.floatbox:last'));
			for (var i = 0; _68.event.keydown.data && i < _68.event.keydown.data.length; i++) {
				_68.event.keydown.data[i].call(e);
			}
		})
	;
	$('form[search]').each(function () {
		var f = this;
		$(f).submit(function () {
			_68.form.trim($(f).find('input'));
			if (f.k.value == '' || f.k.value.length < 2) { f.k.focus(); return false; }
			if ($(f.t).val() == 'p') $(f).attr('action', '/u/designer');
			else $(f).attr('action', '/work/');
			$(f.t).attr('disabled', true);
			f.submit();
		}).find('input[name=k]').blur(function () { if ($(this).val() == '') $(this).css({ color: '' }); }).focus(function () { $(this).css({ color: 'inherit' }); });
	});
	$('a[feedback]').click(function () {
		_68.fb.show('/float/feedback');
		return false;
	});
});


var _68 = {
	signin: '/login?r=' + encodeURIComponent(top.window.location.pathname + top.window.location.search),
	me: true,
	hang: null, //挂起的操作，成功登录调用。
	toplock: null,
	event: {
		keydown: {
			data: [],
			add: function (name, call) { this.data.push({ name: name, call: call }) },
			remove: function (name) { for (var i = 0; this.data && i < this.data.length; i++) { if (this.data[i].name == name) this.data.splice(i, 1); } }
		}
	},
	init: function (e) {
		_68.me = false;
		if (typeof e == 'undefined') e = $(document);
		e.find('select').each(function () {
			var s = $(this);
			s.find('option').css('color', function () { return this.style.color == '' ? s.css('color') : this.style.color; });
		}).change(function () {
			var s = $(this).blur();
			s.css('color', function () {
				var o = s.find('option:selected');
				return o.length > 0 ? o[0].style.color : '';
			});
		}).change();

		e.find('input[verifycode]').focus(function () {
			var vimg = $(this.form).find('img[verifycode]');
			if (!vimg.is(':visible')) {
				vimg.click(function () {
					$(this).attr('src', '/verify?' + Math.random())
				}).click().show().css({ verticalAlign: 'top', cursor: 'pointer' }).attr('title', '点击刷新');
			}
		});

		e.find('select[child]').change(function () { //带child属性的select项子项获取事件处理，忽略大小写，暂时只支持两级。
			var s = $(this), c = s.attr('child'), v = s.attr('val');
			s.parent('label').height('auto').width('auto');
			var o = s.find(':selected');
			if (typeof o.attr('ischd') != 'undefined') return;
			s.find('[ischd]').remove();
			if (o.val() == '') return;
			$.json('/do?' + c, { c: o.val() }, function (r) {
				if (typeof r == 'object' && r != null) {
					var t = o;
					for (var i = 0; i < r.length; i++)
						t = $('<option />').text('　' + r[i].name).attr('value', r[i].code.toLowerCase()).attr('ischd', '').insertAfter(t);

					if (typeof v == 'string') {
						if (v != '') s.val(v.toLowerCase());
						s.removeAttr('val');
					}

					if (typeof s.attr('click') != 'undefined') {
						s.parent('label').height(s.outerHeight()).width(s.outerWidth());
						s.css({ zIndex: 65536, background: '#fff', position: 'absolute', height: 'auto', padding: s.css('padding-left') }).attr('size', s.children().length > 20 ? 20 : s.children().length);
					}
				}
			});
		}).click(function () {
			var s = $(this); s.attr('click', '');
			if (typeof s.attr('size') != 'undefined')
				s.css({ position: '', height: '', padding: '' }).removeAttr('size');
		}).parent('label').css('display', 'inline-block').css('vertical-align', 'middle');

		e.find('select[val]').each(function () { //带val属性的select项赋值操作，忽略大小写，并触发change事件。
			var s = $(this), v = s.attr('val').toLowerCase();
			if (v != '') {
				s.find('option').each(function () {
					var ov = $(this).val();
					if (ov != '' && v.indexOf(ov.toLowerCase()) == 0) {
						$(this).attr('selected', true);
						return false;
					}
				});
			}
		}).change();

		e.find('a[linkout]').each(function () { $(this).attr('href', $(this).attr('linkout') == '' ? $(this).text() : $(this).attr('linkout')).attr('target', '_blank'); });

		//预定义操作
		e.find('[checklogin]').each(function () {
			var t = $(this);
			t.off(t.attr('checklogin')).on(t.attr('checklogin'), function () {
				if (typeof t.attr('loggedin') != 'undefined') return false;
				$.json('/do?check.login', null, function (r) {
					if (r.status != 1) {
						_68.hang = function () { t.trigger(t.attr('checklogin')); };
						_68.fb.show('/float/login');
					}
					else {
						t.attr('loggedin', '');
						t.trigger(t.attr('checklogin'));
						t.removeAttr('loggedin');
					}
				}, true);
				return false;
			});
		});
		e.find('[addworkfav]').on('click', function () {
			if (typeof $(this).attr('loggedin') != 'undefined') {
				$.json('/do?workfav.add', { wid: $(this).attr('addworkfav') }, function (r) {
					if (r.status == -1024) { r.status = 0; _68.fb.show('/float/login'); }
					else if (r.status == 1) alert('已成功收藏。');
					else alert(r.msg);
				});
			}
			return false;
		});
		e.find('[addfriend]').on('click', function () {
			if (typeof $(this).attr('loggedin') != 'undefined') {
				$.json('/do?friend.add', { uid: $(this).attr('addfriend') }, function (r) {
					if (r.status == -1024) { r.status = 0; _68.fb.show('/float/login'); }
					else if (r.status == 1) alert('已成功添加，请等待对方通过。');
					else alert(r.msg);
				});
			}
			return false;
		});
		e.find('[addfocus]').on('click', function () {
			if (typeof $(this).attr('loggedin') != 'undefined') {
				$.json('/do?focus.add', { uid: $(this).attr('addfocus') }, function (r) {
					if (r.status == -1024) { r.status = 0; _68.fb.show('/float/login'); }
					else if (r.status == 1) alert('已成功添加。');
					else alert(r.msg);
				});
			}
			return false;
		});
		e.find('[certsupport]').on('click', function () {
			if (typeof $(this).attr('loggedin') != 'undefined') {
				$.json('/do?cert.support', { uid: $(this).attr('certsupport') }, function (r) {
					if (r.status == -1024) { r.status = 0; _68.fb.show('/float/login'); }
					else if (r.status == 1) alert('已成功辅助。');
					else alert(r.msg);
				});
			}
			return false;
		});
		e.find('[voteassent]').on('click', function () {
			var e = $(this);
			$.json('/do?vote.assent', { t: $(this).attr('voteassent') }, function (r) {
				if (r.status == 1) {
					var n = e.find('[num]');
					if (n.length <= 0) n = e;
					n.text(parseInt(r.msg));
				}
			});
			return false;
		});
		e.find('[sendmsg]').on('click', function () {
			_68.fb.show('/float/msg?uid=' + $(this).attr('sendmsg'));
			return false;
		});
		e.find('[invitation]').on('click', function () {
			_68.fb.show('/float/invitation?uid=' + $(this).attr('invitation'));
			return false;
		});

		e.find('a[pwdfundforgot]').on('click', function () {
			_68.fb.show('/float/pwdfundforgot');
			return false;
		});
		e.find('a[disabled]').on('click', function () { return false; });
		//end

		_68.me = true;
	},
	con: {
		regemail: /^[a-zA-Z0-9-_]+(\.[a-zA-Z0-9_-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z]+)+$/,
		regurl: new RegExp('^https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$')
	},
	cookie: function (name, value) {
		$.cookie(name, value, { path: '/', domain: '68design.net' });
	},
	scroll: function (v) {
		$('html,body').animate({ scrollTop: v }, 300);
	},
	center: function (v) {
		v.each(function () {
			var e = $(this);
			e.css('height', 'auto');
			var x = 0, y = 0;
			if ($.browser && $.browser.msie && $.browser.version == '6.0') {
				x = $(window).scrollLeft() + ($(window).width() - e.outerWidth()) / 2;
				y = $(window).scrollTop() + ($(window).height() - e.outerHeight()) / 2;
			}
			else {
				x = ($(window).width() - e.outerWidth()) / 2;
				y = ($(window).height() - e.outerHeight()) / 2;
			}
			if (y < 0) { y = 0; e.css('height', $(window).height() - (e.outerHeight() - e.height()))/*.css('overflow', 'auto')*/; }
			e.css('top', y).css('left', x);
		});
		return v;
	},
	//遮罩层显示或者取消处理
	shelter: function () {
		if ($('.floatbox').length > 0) { if ($('#dvShelter').length <= 0) $('<div id="dvShelter"></div>').appendTo(document.body); }
		else $('#dvShelter').remove();
	},
	form: {
		trim: function (el) {
			$(el).filter('input[type=text], textarea').each(function () { $(this).val($.trim($(this).val())); });
		}
	},
	fmsg: {
		show: function (obj, msg, suc) {
			this.clear(obj);
			if (!suc) $(obj).addClass('g_err');
			if (typeof msg == 'string' && msg != '') {
				var m = $('<div class="g_fmsg' + (suc ? ' g' : '') + '"><div class="c"> ' + msg + '</div><div class="a"><div class="line10"></div><div class="line9"></div><div class="line8"></div><div class="line7"></div><div class="line6"></div><div class="line5"></div><div class="line4"></div><div class="line3"></div><div class="line2"></div><div class="line1"></div></div></div>');
				$(obj).after(m);
				var p = $(obj).position();
				m.css({ 'top': p.top - m.outerHeight(), 'left': $(obj).outerWidth() - 20 + p.left }).show();
			}
		},
		clear: function (obj) {
			if (typeof obj == 'undefined') {
				$('form .g_err').removeClass('g_err');
				$('form .g_fmsg').remove();
			}
			else if ($(obj).is("form")) {
				$(obj).find('.g_err').removeClass('g_err');
				$(obj).find('.g_fmsg').remove();
			}
			else $(obj).removeClass('g_err').next('.g_fmsg').remove();
		}
	},
	tip: {
		show: function (e, text, bgcolor) {
			e = $(e).offset();
			$('<div class="g_ftip"' + (typeof bgcolor != 'undefined' ? ' style="background:' + bgcolor + ';"' : '') + '>' + text + '</div>').appendTo(document.body).css({ left: e.left, top: e.top }).fadeIn(300, function () { $(this).delay(3000).fadeOut(300, function () { $(this).remove(); }); });
		}
	},
	fb: {
		callback: null, //显示后要执行的
		clear: function () { $('.floatbox').each(function () { _68.fb.hide($(this)); }); },
		//显示一个指定url的浮动框，参数fbox为可选现有浮动框。
		show: function (url, fbox) {
			if (fbox == null || typeof fbox == 'undefined') {
				if (url == '') return;
				fbox = $('<div class="floatbox" data-position-to="window"><a href="#" onclick="return false;" class="close" cancel>×</a><div class="fview"></div></div>');
			}
			if (url == '') url = fbox.find('div[url]').attr('url');
			fbox.find('.fview').load(url, fbox.find('form').serializeArray(), function (rt, ts, xhr) {
				if (ts == 'error' || ts == 'timeout') { _68.fb.hide(fbox); alert('操作失败：\r\n您可能没有权限或者重复提交！\r\n\r\n请刷新页面重新尝试。'); return; }
				if ($(document.body).find(fbox).length <= 0) {
					_68.center(fbox.appendTo(document.body));
					fbox.fadeIn(300, function () {
						$(window).resize(); _68.fb.bind(fbox);
						if (fbox.find('[focus]').eq(0).focus().length <= 0)
							fbox.find('input[type=text]:enabled,input[type=password]:enabled,select:enabled,textarea:enabled,input[type=submit]').eq(0).focus();
					}).focus();
					_68.shelter();
				}
				else _68.fb.bind(fbox);
			});
			_68.toplock = $(window).scrollTop();
			return fbox;
		},
		bind: function (fbox) {
			if ($.trim(fbox.find('.fview > div').children().not('script').text()) == '') {
				fbox.hide();
				if (typeof _68.fb.callback == 'function') { _68.fb.callback(fbox); _68.fb.callback = null; }
				_68.fb.hide(fbox);
				return;
			}
			_68.init(fbox);
			fbox.find('[cancel]').click(function () { _68.fb.hide(fbox); });
			if (typeof _68.fb.callback == 'function') { _68.fb.callback(fbox); _68.fb.callback = null; }
			fbox.find('form').submit(function () {
				if ($(this).attr('valid') != 'true') return false;
				$(this).find('input[type=submit]').attr('disabled', true);
				_68.fb.show('', fbox);
				return false;
			});
			fbox.find(".title").mousedown(function (e) {
				var px = e.pageX - fbox.offset().left;
				var py = e.pageY - fbox.offset().top;
				$(document).mousemove(function (e) { fbox.offset({ left: (e.pageX - px), top: (e.pageY - py) }); }).mouseup(function () { $(document).unbind("mousemove"); });
			});
		},
		hide: function (fbox, callback) {
			_68.toplock = null;
			_68.fb.callback = null;
			if ($(document.body).find(fbox).length > 0) {
				fbox.stop().fadeOut(300, function () {
					fbox.remove();
					_68.shelter();
					//if ($('#dvShelter').length <= 0) $('.ui-datepicker').remove();
					if (typeof callback == 'function') callback();
				});
			}
		}
	},
	checkall: function (all, array) {
		all.click(function () { array.prop('checked', all.prop('checked') ? true : false); });
	},
	checkallval: function (array) {
		var v = '';
		array.each(function () { v += (v == '' ? '' : ',') + $(this).val(); });
		return v;
	},
	inputlength: function (input, maxlength, tip) {
		input.keyup(function (event) { var cl = input.val().replace(/\r?\n/g, "00").length; tip.html(maxlength - cl < 0 ? ('<em style="color:red;">' + (maxlength - cl) + '</em>') : maxlength - cl); }).keyup();
	},
	picview: {
		show: function (ct, ch, call) {
			_68.picview.hide();
			var ms = ct.find('img');
			if (!ch.is('img')) ch = ch.find('img');
			var i = -1; var vsrc = (typeof ch.attr('org') == 'undefined' ? ch.attr('src') : ch.attr('org'));
			for (j = 0; j < ms.length; j++) if (ms.eq(j).attr('org') == vsrc) i = j;
			var v = $('<div class="_picView" data-position-to="window"><a href="#" onclick="return false;" class="close"></a><a href="#" onclick="return false;" class="prev"></a><img src="/img/loading4.gif" class="load" /><div class="pbox"><img onmousedown="return false;" onmousemove="return false;" src=' + vsrc + ' /></div><a href="#" onclick="return false;" class="next"></a></div>').appendTo(document.body).bind('mousewheel', function (event, delta, deltaX, deltaY) {
				var pb = v.find('.pbox');
				var o = delta > 0 ? 100 : -100;
				var y = parseInt(pb.css('marginTop').replace('px', '')) + o;
				if (y > 0) y = 0;
				else if (y < v.height() - pb.height()) y = v.height() - pb.height();
				pb.css('marginTop', y);
				return false;
			});
			v.find('.close').click(function () { _68.picview.hide(); });
			if (i <= 0) v.find('.prev').hide(); else v.find('.prev').bind('click', function () { _68.picview.show(ct, ms.eq(i - 1), call); });
			if (i < 0 || i >= ms.length - 1) v.find('.next').hide(); else v.find('.next').bind('click', function () { _68.picview.show(ct, ms.eq(i + 1), call); });
			_68.event.keydown.add('picview', function (e) { if (e.keyCode == 27) { _68.picview.hide(); } else if (e.keyCode == 37 && i > 0) { _68.picview.show(ct, ms.eq(i - 1), call); } else if (e.keyCode == 39 && i >= 0 && i < ms.length - 1) { _68.picview.show(ct, ms.eq(i + 1), call); } });
			v.find('.pbox img').load(function () {
				v.find('.load').hide();
				var pb = v.find('.pbox');
				var x, y, mx, my;
				pb.bind('mouseenter mouseleave', function () { $(document).unbind('mousemove'); });
				v.bind('mouseenter mouseleave', function () { $(document).unbind('mousemove'); });
				pb.css('marginTop', v.height() > pb.outerHeight() ? (v.height() - pb.outerHeight()) / 2 : 0).css('marginLeft', (v.width() - pb.outerWidth()) / 2).bind('mouseup touchend', function () { $(document).unbind('mousemove touchmove'); }).bind('mousedown touchstart', function (event) { event.preventDefault(); x = event.pageX || event.originalEvent.changedTouches[0].pageX; y = event.pageY || event.originalEvent.changedTouches[0].pageY; mx = parseInt(pb.css('marginLeft').replace('px', '')); my = parseInt(pb.css('marginTop').replace('px', '')); $(document).bind('mousemove touchmove', function (event) { pb.css({ marginLeft: (event.pageX || event.originalEvent.changedTouches[0].pageX) - x + mx, marginTop: (event.pageY || event.originalEvent.changedTouches[0].pageY) - y + my }); }); }).fadeIn(200);
			});
			if (typeof call == 'function') call(v, ms.length, i);
		},
		hide: function () { _68.event.keydown.remove('picview'); $(document).unbind('mousemove mouseenter'); $('._picView').unbind('mousewheel').remove(); }
	},
	upfile: {
		isup: false,
		init: function (n, w) {
			if (typeof w == 'undefined') w = window;
			w.$('._upfile' + (typeof n == 'undefined' ? '' : '[fn="' + n + '"]')).each(function () {
				var uf = $(this);
				$.json('/do?upfile.get', { n: uf.attr('fn') }, function (r) {
					uf.html('');
					for (var i = 0; r != null && i < r.length; i++)
						uf.append('<span><a href="#" onclick="return false;" g="' + r[i].guid + '">删</a>' + r[i].name + '</span>');
					uf.append($('<input type="button" value="上传" class="g_ib" />').click(function () { _68.upfile.create(uf.attr('fn'), w); }));
					uf.find('a').click(function () { _68.upfile.del(uf.attr('fn'), $(this).attr('g'), w); });
				});
			});
			if (typeof n == 'undefined') {
				if ($('iframe#upframe').length > 0) $('iframe#upframe').remove();
				$('<iframe id="upframe" src="/up" width="0" height="0" scrolling="no" frameborder="0" style="display:none;"></iframe>').appendTo($(document.body));
			}
		},
		has: function (n, w) {
			if (typeof w == 'undefined') w = window;
			return w.$('._upfile[fn="' + n + '"] span').length > 0;
		},
		create: function (n, w, data) {
			if (typeof w == 'undefined') w = window;
			if (_68.upfile.isup || $('iframe#upframe').length <= 0) return;
			var uw = document.getElementById('upframe').contentWindow;
			var f = $(uw.document).find('form input[type=file]');
			if (f.length <= 0) { uw.location = '/up'; return; }
			$.json('/do?upfile.new', $.extend({ n: n }, typeof data == 'string' ? $.parseJSON(data) : null), function (r) {
				if (!r || !r.u || !r.g) return;
				f.change(function () { _68.upfile.show(); f[0].form.submit(); _68.upfile.isup = true; window.setTimeout(function () { _68.upfile.doing(n, r.g, uw, w); }, 2000); });
				$(f[0].form).attr('action', r.u + 'transfer?g=' + r.g);
				f.click();
			}, true);
		},
		del: function (n, g, w, call) {
			if (typeof w == 'undefined') w = window;
			$.json('/do?upfile.remove', { n: n, g: g }, function (r) { if (typeof call == 'function') call(); else _68.upfile.init(n, w); });
		},
		show: function () {
			if ($('#upShelter').length <= 0) $('<div id="upShelter"></div>').appendTo(document.body);
			if ($('#upMsg').length <= 0) $('<div id="upMsg">准备上传</div>').appendTo(document.body).show().focus();
			$(window).resize();
		},
		hide: function () {
			$('#upMsg').remove();
			$('#upShelter').remove();
		},
		doing: function (n, g, uw, w) {
			$.json('/do?upfile.status', { g: g }, function (r) {
				if (r == null) {
					uw.location = '/up';
					_68.upfile.isup = false;
					$('#upMsg').html('已取消 请重新上传<br/><input type="button" value="确定" onclick="_68.upfile.hide();" />');
					$(window).resize();
				}
				else if (r.status == 'Uploading') {
					$('#upMsg').html('正在上传 ' + r.progress + '%');
					$(window).resize();
					window.setTimeout(function () { _68.upfile.doing(n, g, uw, w); }, 1000);
				}
				else if (r.status == 'Completed') {
					uw.location = '/up';
					var uf = w.$('._upfile[fn="' + n + '"]');
					_68.upfile.isup = false;
					_68.upfile.hide();
					_68.upfile.init(n, w);
					if ((uf.data('events') && uf.data('events')['complete']) || ($._data(uf[0], 'events') && $._data(uf[0], 'events')['complete']))
						uf.trigger('complete');
				}
				else if (r.status == 'Destroyed') {
					uw.location = '/up';
					_68.upfile.isup = false;
					$('#upMsg').html('已取消 [' + r.error + ']<br/><input type="button" value="确定" onclick="_68.upfile.hide();" />');
					$(window).resize();
				}
			});
		}
	},
	flw: {
		//评论留言回复
		reply: {
			temp: null,
			show: function (e, tid, pc) {
				e = $(e);
				var r = e.nextAll('.hf-box').length > 0;
				if (this.temp != null) { this.temp.remove(); e.nextAll('.hf-box').remove(); }
				if (r) return;
				$.get('/load/flwreply', function (h) {
					_68.flw.reply.temp = $(h).appendTo(e.parent('.box-hf'));
					var f = _68.flw.reply.temp.find('form');
					_68.init(f);
					f.find('input[name=tid]').val(tid);
					f.submit(function () { return _68.flw.submit(f[0], pc); });
					f.find('textarea').focus();
				})
			}
		},
		submit: function (f, pc) {
			f.body.value = $.trim(f.body.value);
			if (f.body.value == '') { f.body.focus(); return false; }
			$.json('/do?flw.submit', $(f).serializeArray(), function (r) {
				if (r.status == -1024) { r.status = 0; _68.fb.show('/float/login'); }
				else if (r.status == 1) {
					$(f.body).val('').blur();
					alert('已成功提交。');
					pc(0);
				}
				else alert(r.msg);
			});
			return false;
		}
	}
}