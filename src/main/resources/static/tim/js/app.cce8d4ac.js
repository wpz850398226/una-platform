(function (e) {
    function t(t) {
        for (var i, o, a = t[0], c = t[1], l = t[2], m = 0, p = []; m < a.length; m++) o = a[m], Object.prototype.hasOwnProperty.call(n, o) && n[o] && p.push(n[o][0]), n[o] = 0;
        for (i in c) Object.prototype.hasOwnProperty.call(c, i) && (e[i] = c[i]);
        u && u(t);
        while (p.length) p.shift()();
        return r.push.apply(r, l || []), s()
    }

    function s() {
        for (var e, t = 0; t < r.length; t++) {
            for (var s = r[t], i = !0, a = 1; a < s.length; a++) {
                var c = s[a];
                0 !== n[c] && (i = !1)
            }
            i && (r.splice(t--, 1), e = o(o.s = s[0]))
        }
        return e
    }

    var i = {}, n = {app: 0}, r = [];

    function o(t) {
        if (i[t]) return i[t].exports;
        var s = i[t] = {i: t, l: !1, exports: {}};
        return e[t].call(s.exports, s, s.exports, o), s.l = !0, s.exports
    }

    o.m = e, o.c = i, o.d = function (e, t, s) {
        o.o(e, t) || Object.defineProperty(e, t, {enumerable: !0, get: s})
    }, o.r = function (e) {
        "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(e, "__esModule", {value: !0})
    }, o.t = function (e, t) {
        if (1 & t && (e = o(e)), 8 & t) return e;
        if (4 & t && "object" === typeof e && e && e.__esModule) return e;
        var s = Object.create(null);
        if (o.r(s), Object.defineProperty(s, "default", {
            enumerable: !0,
            value: e
        }), 2 & t && "string" != typeof e) for (var i in e) o.d(s, i, function (t) {
            return e[t]
        }.bind(null, i));
        return s
    }, o.n = function (e) {
        var t = e && e.__esModule ? function () {
            return e["default"]
        } : function () {
            return e
        };
        return o.d(t, "a", t), t
    }, o.o = function (e, t) {
        return Object.prototype.hasOwnProperty.call(e, t)
    }, o.p = "";
    var a = window["webpackJsonp"] = window["webpackJsonp"] || [], c = a.push.bind(a);
    a.push = t, a = a.slice();
    for (var l = 0; l < a.length; l++) t(a[l]);
    var u = c;
    r.push([0, "vendors~app"]), s()
})({
    0: function (e, t, s) {
        e.exports = s("56d7")
    }, "0270": function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAwCAYAAABnjuimAAAACXBIWXMAACE4AAAhOAFFljFgAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAOTSURBVHgB7ViLUdtAEH0nAgyfzEgdmAowFcRUEFMBTgUhFWAqACqAVJChApwKcCrAqcAi4ZMh4OPt2TLGvpVlkMQww5sx2Lrf097e3ts1yAnxlbW+5+GKMcgBAd4I3onmjXeieSMz0W7XVuNre4qcIHNxzkrW/pmIXlzbfbOAM1jUkBc4F+c8v7ixu1m6pxKVN5Y3Z4TcQUGwPTQZg8+mWVclKgP5xqe5WlFHVdZKI+slOiQJqAMLQCWN7ARRdgxfgWSChGw43jBBNFjAPl6HZILKgMMTPCHavbQNKouGNgPb9pATeIj2UtZpdP/Y+uizobKZ5pdCMloxTSh4jnrq/rVNE0ALT7G9xVoUmVh+DC1Kc2+rJC0O00g+F9FH06RlD5XmEAuPYdG97eAAncFH1KATLps1TMFz9ahbe9FdJhXftIlV+xadRx2aNXvYQoEQEvYeX5TmkNwa8sUR5Stv+3rx+XG0atrIALGc75NlLF2gRYu2vBwCfHb/B4fo3NeJ1tx0k5QAHqwaSXlFD7c/CjCHqnckfbMskoLBWrG3ka4ZkFDN12aUrSgS3MHvSlM1oBet+1p69zhB2bDwnwfD20r+eBsDdFA25pUDBazLqa94B/1/BaL/FB9lUDFFFw5mhcbnTSV3XnP7NGHRSFkzVonSsSsoG/qaHSGqXZFVlA/t8okDBtnfMw0qEIHBJ99zcvwVqGLA+IVKkeBxr3kbemgFuFevylCEAkqCpEHQYjo5Bk7qa1adQ6YqRh7QpCYbWkPhrKYDLD6UYVVnTUUcJUJlNBURTeqLYx3qwY0kycqdZFpSOZIGOYu6dEBPsirMaY5QEExKHWE0pX68Qu9wIG/gnc2inrXqNgsurtycdW+jCPdVc5z8HBKdkmS5qpuUH5EDxNVI8ojhqKn1GefyRJRIOpDiApLf71DdnM9SgPWQdJW7aRWZ8TTIK+XiS1aWTXq5UTLU3i0njEwH2QiGwSJ2p9ZaDdo8QBuTj5VJU4oC4zPILpy4NOIO7SQ6OCXUFxk1l/JmqbPSL+0SI4yZjDB6XeiGYaOHHyjrzqclrcFWtOTfIVU4ywDGz01ulZYZ5gZZg5bc1EgKMqUbvDl2uH1fM7nCbIhJco9h6GBax8x5kbgCxUEzL1XlDuMyvvn8Uek/G4aEA2rH2S3cvwE/sKa1lC1aJHhRpjkQLHKq1x3pfo0g0Qvx4NPm9v6UqPCSEtEDnYmShE0MrBMAAAAASUVORK5CYII="
    }, "046b": function (e, t, s) {
    }, "04f1": function (e, t, s) {
        "use strict";
        s("c55f")
    }, "0553": function (e, t, s) {
    }, "0716": function (e, t, s) {
        "use strict";
        s("4eff")
    }, "0c23": function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAIk0lEQVR4Xu1ba4xdVRVe69RO7AR1jAlGCzEq/jD4QrRIqA8UEBMLPqi2PlsbhnYmvXudUcR3rajIYO9Z+zYzwg3SsSXWWImIP1qrKKEEApRHwEeiKWigqE3EG3UY49izzGrOJb37nsc+5947rWl3cnIzPev5nbX3XnvtVYQTfOAJ7j+cBOBkBCwAAkT0DhG5EBFPBQB9XiwipyZ/g4gcQsRDAPBXADikfwdBsCeKon2DNm9gU0CdBoD3Jc/LKjryBADsDIJgb71ev72ijFy2vgNARJ8CgCsAYFk/DUbE34vIzfPz89NTU1N/65fsvgFQq9VWBUEwBgBv7ZdxGXIOAMB0q9WanpmZ+XevunoGIAzDFXEcjyHixb0aU5L/0TiOpxuNxvUl+TrIewIgDMP1IvKdEgbcgYhPxnH8ZBAET+iv8gZBcFocx6frr4icBgC6fviOJjPrlKs0KgNARF8GgK8VaP0TANwJAL8EgFuZueVj5caNG58fBIHuGhcCgD6vKOD7BTMrXelRCQAi+jkAXJCj7QEA0C/TLG1RCgMRjQKAPmfnyHuMmV9ZVl9pAIhIFspxV48PEMxcyqdSxESkK3BWON7AzOvLfoEq9ET0QwBYmcF7PzN7b8HeABSE/WZm/moVZ6ry5IGAiNNRFI37yPYCgIhuSOZgmswFd75thDFmChE190gb1zLz54pAKASgVqutD4Iga6vbzsyfLFIyyPfGmElEvDJNRxzHG4ryhFwA1qxZ89yRkZH7AOC1KQruYObzB+mcr2xjzE2IuDaF/tFWq7UsL2PMBYCIJgBgS4rgBw8ePHjurl27/uNr5CDpNm3a9JxWq7UHEd+VoufTzFzP0p8JwPj4+IsWL158LwC4e+szQRAsr9frDw3SqbKyJyYmzorj+C4AGHZ4D8zPz5+TdYDKBCAn0ztmi14RKESkO9GmFLqvMPPVafx5APwWAF59NJOIPDQ3N7e82Ww+U2SMvk8SlzkA0FT1zz48bRoiegkArACAOWbe4cM7Ojo6vGTJkrsQ8Sw3Cpj5DG8AarXa2UEQ7HcZEHF1FEU/8DHGyRifEhGy1u7y4TXGrEREBoCXtul9M7wwDFeJyE5Xj4hcaq29rcunNIOIiAAgct7tZ+Y3+zhARB8HgO0pRnyoCITEec303HGF79kiDMOHReT1joAbmflyXwB+BgAXOcTXMPMXegFAeUUkE4Qc55XVGwAiagDARmf6/sVaq9OqY3StATqPhoeHZ13CIAjeWa/Xf+UJgCrSKfRsCDvGdIFQ4PxTAPAm33XEGPNeRPypa2scxxc0Go2O2mIXAGEYXiwiux3mWWY+xcf5Nk2BQx2RUIbW1wYi0oV6iQP8N621Xzz637oAIKINWnNzFO1l5nf7Ki8DgtIiYtqcPyImb8rk2UNEadO4ax1IA+A6APiMg9w2a61We0uPoq+bJ7Cq8yozIz2+jZkvzY0AY8yPEPGDDgBft9ZqCazSqAJCL84nAFyNiF9yDL6Xmd9SNAW0nPXGDiLEDVEU9VR9LQNCr86r7WkFWxF53FrbUdBJWwSfFpEXOgBcEkVR16paNhx8QOiH8wkAK0TETXy6FvMuAIwxTyNiBwBZWdTxDIAx5hJE/Ilj47+Y+XlFU+B+3XOdCBiPosjdGUr57/P12wL7EQVhGI6JyJRj5AH3TJC2C2iu/2FnEfyWtfbzpTw+iriM8/0CwRhzDSJ2lMQQ8e4ois7LjYAwDL8hIh0pLyLeHEWR5velRxXn+wFCGIY7RORjzof8sbX2A0Xb4DpEvNHxtFL5q8h5DXXVM6BESNP2jis2EbneWquJ3rMjbQooU1fOf/jw4Rds3br1H74h4ON8+2RYhtZHPxGNAMDfU2i7ijmpBREiUkc7VksRucxae4unAW9IQFRDukbaIlcAgt4pns/MD3vqXwMA21xaPSJbax/JjQB9SUTfB4DVjgDvm5+kSeK7vs636fJAQETvnYiItBbhrlm/YebXuDalRkAYhmtF5CaH2PvyMcsRn+0tB4S1zDzjGQF/BAC3LSf1oiQLgDNE5A8pykJm1lJV4SAirQe0b3N7LYk9wMwduUmWARnVLIjj+LxGo3G3VwQokTHmHkTsODgAgN73n+tTmBgbGztlaGjoI1rU7LUoOjQ0dOvk5OQ/i1BPCqn3pHz9+5j5nDT+vKqwHn/T5vEkM19VZMyxeE9E1wLAZ1N0r2Nmd0ofISu6GdLuDrfpSW+DNAoePBZO5oS+nmD16w85NPuY+W1ZfLkAJJ1fXSVmAPi/uRqL43h1o9HILOUX3g4bY3ZndIBVyg4HETVZl6Missda+548nYUAaBtcyrm6LfO4vh5HxMI6RiEASWKU2REmItdZa9MWnkF87A6ZRPQ9APhE6uruWcXyAiABoeuY3FYsIttGRkZGN2/e/N+Be63NQStXDi1dulSrvln9hJmXod55QJojRPRrADgz7Z2I3C4iVzUaDa0pDmwk95baftdRtzxKYameQe8IaCsoaJNTsmYcx81+A5E43u4XzATY9xK1LaA0AMpojNmJiKsKPnNTzxPWWm2yqDzCMFwmIutymrTash9hZvdCtFBvJQASEC5HRJ9OUAVgDwDMMLMeUgpHGIZLRUT/r8Flnn3DO5g5dTEsUlYZgAQE7efVwuOrihQl7wfRLH0lM3/bU38XWU8AqLSJiYnT4zjWZirtKViwISK3IOIWZtb0t/LoGYC25lqttnzRokUTIvL+ytb4Me5Xx307VYpE9g2AtiIi+mjSnJB6/CwyKOf97xBx++zs7JZmsznfg5wO1r4D0JZujHmdniEQ8SIRSevf8/FhLwDsjuN4X7+31bbygQFwtHdhGGqjwttF5OXaba6/iHjkUTq9tNQHER8HgMf0d3Z29k7fbjQfJLNoFgSAXgwcNO9JAAaN8PEu/4SPgP8BZlEEfapc9ooAAAAASUVORK5CYII="
    }, "0c24": function (e, t, s) {
    }, "0e57": function (e, t, s) {
        "use strict";
        s("b264")
    }, 1037: function (e, t, s) {
    }, 1239: function (e, t, s) {
        "use strict";
        s("046b")
    }, "12d4": function (e, t, s) {
    }, 1376: function (e, t, s) {
        "use strict";
        s("de78")
    }, 1474: function (e, t, s) {
        "use strict";
        s("5334")
    }, "199a": function (e, t, s) {
        "use strict";
        s("8831")
    }, "1b70": function (e, t, s) {
    }, "1f0e": function (e, t, s) {
        "use strict";
        s("a777")
    }, 2054: function (e, t, s) {
        "use strict";
        s("fc3e")
    }, 2482: function (e, t, s) {
        "use strict";
        s("fee6")
    }, "24c8": function (e, t, s) {
        "use strict";
        s("e8d4")
    }, "275a": function (e, t, s) {
    }, "2af2": function (e, t, s) {
    }, "2daf": function (e, t, s) {
    }, "2ee3": function (e, t, s) {
    }, "32c9": function (e, t, s) {
        "use strict";
        s("1b70")
    }, "33ad": function (e, t, s) {
        e.exports = s.p + "img/open-mic.71add3a2.png"
    }, 3763: function (e, t, s) {
        "use strict";
        s("c964")
    }, 3801: function (e, t, s) {
        "use strict";
        s("7a96")
    }, 3918: function (e, t, s) {
    }, "3a6b": function (e, t, s) {
        "use strict";
        s("2ee3")
    }, "3cfd": function (e, t, s) {
    }, "3dbe": function (e, t, s) {
        e.exports = s.p + "img/no-video.f64769eb.png"
    }, "3f2d": function (e, t, s) {
        "use strict";
        s("d94e")
    }, 4011: function (e, t, s) {
        "use strict";
        s("7a62")
    }, "406c": function (e, t, s) {
    }, "406d": function (e, t) {
        e.exports = "data:image/gif;base64,R0lGODlhKgAqAIABAP///////yH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQ1IDc5LjE2MzQ5OSwgMjAxOC8wOC8xMy0xNjo0MDoyMiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTkgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QzFGOTY5Nzk1NzgwMTFFQTk0MUFDRTM0NzlBQjQ1Q0YiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QzFGOTY5N0E1NzgwMTFFQTk0MUFDRTM0NzlBQjQ1Q0YiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDMUY5Njk3NzU3ODAxMUVBOTQxQUNFMzQ3OUFCNDVDRiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDMUY5Njk3ODU3ODAxMUVBOTQxQUNFMzQ3OUFCNDVDRiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgH//v38+/r5+Pf29fTz8vHw7+7t7Ovq6ejn5uXk4+Lh4N/e3dzb2tnY19bV1NPS0dDPzs3My8rJyMfGxcTDwsHAv769vLu6ubi3trW0s7KxsK+urayrqqmop6alpKOioaCfnp2cm5qZmJeWlZSTkpGQj46NjIuKiYiHhoWEg4KBgH9+fXx7enl4d3Z1dHNycXBvbm1sa2ppaGdmZWRjYmFgX15dXFtaWVhXVlVUU1JRUE9OTUxLSklIR0ZFRENCQUA/Pj08Ozo5ODc2NTQzMjEwLy4tLCsqKSgnJiUkIyIhIB8eHRwbGhkYFxYVFBMSERAPDg0MCwoJCAcGBQQDAgEAACH5BAUIAAEALAAAAAAqACoAAAJ5jI+py+0Po5y02ouzrqD7HXkf+IgdWZqoYwJr075MLCu0dh85tge91ftxVAkhJUjEJXVLXtP3BD6NE6QIZB0pr8XokIugSrKnLhg2TZ9nai1zvSC7zG42XA7B08usNv9dF+c3F+ZVeDeYl7hHiIb4GFgjOUlZaTlRAAAh+QQJBAABACwSAAYABgABAAACAoxfACH5BAkEAAEALAAAAAAqACoAAAIvjI+py+0Po5y02ouz3rz7D4biSHLAiZbqyrbuC8fyTNcTmtr6zvf+DwwKh8RioAAAIfkECQQAAQAsAAAAACoAKgAAAn2Mj6nL7Q+jnLTai7PevPsPhgdAlqJSmltKoizHAm4Kv0lc07et4bve442EGB/CmEEalBcm0+IkNqXPShQ4xDKuqqN01s2Gl9/fOMBtPdIys9rBBr8bcXcbTs1rF3XvXn4nNkf2Zwd4uKZ3VuW3qDjI9xhIeHZieYmZqdlQAAAh+QQJBAABACwAAAAAKgAqAAACeYyPqcvtD6OctNqLs968+w+G4gGU5piYZ6oCXOuy6tsqMD3Lq3brJb4j1TY9RDFzNCQvy6WlOeRFhTnjlAGtUoPYayDLXYB/vphjbN6Sz140xF1+t+fabv1LD9vya3XaHgbHdifYUKjktUfI9yfGqKiHIjlJWWlpUAAAIfkECQQAAQAsAAAAACoAKgAAAnuMj6nL7Q+jnLTai7PevPsPYsBIhgtZmgk6qivrIiygzJz9ojecpxou4/2EB2DGaEBelEoLk5iEnqRP3aPqK0pr1K7VgW31xGBvNvpthGljdjmNPgeabS58vq3rg3e7fA0BuKdmRqbVNxhnqOhGeCf49lfYGFNpeYmJWQAAIfkECQQAAQAsAAAAACoAKgAAAnqMj6nL7Q+jnLTai7PevPsPYsBIhgtZmgk6qivrIiwQH7Nyb7kM6z2P4uxsv8zQcLwkl0UGM/iCOp4pYLVBbUWvzmYgS3uAcV6ylHhGlrdaaxvNPXnH4rk9LcfTp/d40uzX99alJxjGV5gYBzj4ZQixB9dYQ1lpeWlSAAAh+QQJBAABACwAAAAAKgAqAAACgYyPqcvtD6OctNqLs968VwCGnhOKI1OCJ5quSwq4CiwndH3ceKDjff3jtWxDUjF3NARnSWGJ+XwEp02oCUGNGrVIrrJK9DqvXTJLnDW/mmmVlA0XW93YuHoeC6uXenrZ/yXXl1eHBla4Zwe4Zth4N4gX+ea4yIdYqUi4s8nZ6YlTAAAh+QQJBAABACwAAAAAKgAqAAAChoyPqcvtD6OctNqLs968VwCGnhOKI1OCJ5quSwq4CiwndH3ceKDjvfR7tT5DUnESnB0NwaRtyYM6EU0pVFnCmqjXZ9a7zXW5X3KYORaX1efomvV2t6dsFdhuxjeqcTq6bxWnpfc3l1ZIKJfopxhz55gHuRdoKPg4KImYKQTY2bYDGio66lIAACH5BAkEAAEALAAAAAAqACoAAAKAjI+py+0Po5y02ouz3hz4z0EfGDqjV5pn2pwAy7jwIs9JbR94HuyST1tVgAriw4hAtoSqkUVpgAadR6bOGsVOSUmtFAjWFr1kapN7NafRsTK7J765UXJ12w7Hf+ev+nusxyci6Ed3ZriGmIUH+BbGWNjXFQg56UjIk6m5ydkZUAAAIfkECQQAAQAsAAAAACoAKgAAAoWMj6nL7Q+jnLTai7NOoPsdeR/4iB1ZmqhjAmvTvkwsK3SN3LihS/3ygwQ5qsowV6QcD8tZEvb0RYFTYZUosjQDv+4VmcWOwGNomHwSp1lT79lc5rW/zPlbfqfm3XF9n78GF4j3R0c4yGXXZ6OIuAXoohbJttc4KXh5mJmYt+P5CRoqelAAACH5BAkEAAEALAAAAAAqACoAAAKAjI+py+0Po5y02ouzTqD7HXkf+IgdWZqoYwJr075MLCt0jdyTPqsVvwBChBwfhZgz7pQ9kQV5gDZHLGbIasOmnFvqklsFf73TU1FskAaAbG0Svbaq2/C5/A7P1vHkIN/81qfXRyd4Rvjn0gUYlTjkeMhYphgoGZeHk6m5ydnJUAAAIfkECQQAAQAsAAAAACoAKgAAAoWMj6nL7Q+jnLTai7NOoPsdeR/4iB1ZmqhjAmvTvkwsK3SN3LihS/3ygwQ5qsowV6QcD8tZEvb0RYFTYZUosjQDv+4VmcWOwGNomHwSp1lT79lc5rW/zPlbfqfm3XF9n78GF4j3R0c4yGXXZ6OIuAXoohbJttc4KXh5mJmYt+P5CRoqelAAACH5BAkEAAEALAAAAAAqACoAAAKAjI+py+0Po5y02ouz3hz4z0EfGDqjV5pn2pwAy7jwIs9JbR94HuyST1tVgAriw4hAtoSqkUVpgAadR6bOGsVOSUmtFAjWFr1kapN7NafRsTK7J765UXJ12w7Hf+ev+nusxyci6Ed3ZriGmIUH+BbGWNjXFQg56UjIk6m5ydkZUAAAIfkECQQAAQAsAAAAACoAKgAAAoaMj6nL7Q+jnLTai7PevFcAhp4TiiNTgiearksKuAosJ3R93Hig4730e7U+Q1JxEpwdDcGkbcmDOhFNKVRZwpqo12fWu811uV9ymDkWl9Xn6Jr1drenbBXYbsY3qnE6um8Vp6X3N5dWSCiX6KcYc+eYB7kXaCj4OCiJmCkE2Nm2AxoqOupSAAAh+QQJBAABACwAAAAAKgAqAAACgYyPqcvtD6OctNqLs968+68A4ggyI1mGJ5CqZ5usLHzIdL3ehq3ziJ8B7nIbYcBodKF+RObrgWzino4odbpsWLPDq0na5R7Bi61IeYaSzbPq+u0tw8XJ2DztFKPbWHyf/xXHBjFoF7eHaKin6BfWOHbICAhJR5aY91ins8nZ6blRAAAh+QQJBAABACwAAAAAKgAqAAACeoyPqcvtD6OctNqLs968+w9iwEiGC1maCTqqK+siLBAfs3JvuQzrPY/i7Gy/zNBwvCSXRQYz+II6nilgtUFtRa/OZiBLe4BxXrKUeEaWt1prG809ecfiuT0tx9On93jS7Nf31qUnGMZXmBgHOPhlCLEH11hDWWl5aVIAACH5BAkEAAEALAAAAAAqACoAAAJ7jI+py+0Po5y02ouz3rz7D2LASIYLWZoJOqor6yIsoMyc/aI3nKcaLuP9hAdgxmhAXpRKC5OYhJ6kT92j6itKa9Su1YFt9cRgbzb6bYRpY3Y5jT4Hmm0ufL6t64N3u3wNAbinZkam1TcYZ6joRngn+PZX2BhTaXmJiVkAACH5BAkEAAEALAAAAAAqACoAAAJ5jI+py+0Po5y02ouz3rz7D4biAZTmmJhnqgJc67Lq2yowPcurduslviPVNj1EMXM0JC/LpaU55EWFOeOUAa1Sg9hrIMtdgH++mGNs3pLPXjTEXX6359pu/UsP2/JrddoeBsd2J9hQqOS1R8j3J8aoqIciOUlZaWlQAAAh+QQJBAABACwAAAAAKgAqAAACfYyPqcvtD6OctNqLs968+28B4ggyI1kqp8itgLq2ceLKJ3xvNT1rO/LLBA3DS7EY6h2QlaMS48z5ngEmJYqaSpdUK3dL7FJx2W85DF5gWbw0mQ0Uu9vnqrz+ftHhZr7p7mc3F5e2pudgmAeRuHfYwEiI16g4mWJ5iZmpiVEAACH5BAUEAAEALAAAAAAqACoAAAJ8jI+py+0Po5y02ouz3hyCD3YPGIoN+ZknqjIo0C4vNye1dh85tge91ftVgqwNkUQrIoSUYwmn1EV5U18VWGVOnClj9oscgZ/SsIMLs13V5jLZoIWPu8t1vS3HW/UKdN/uRheYdvcmM0c46IH4x8dm6CemFxlTaXmJmXlRAAA7"
    }, 4213: function (e, t, s) {
    }, 4602: function (e, t, s) {
    }, "464f": function (e, t, s) {
        "use strict";
        s("1037")
    }, "47b1": function (e, t, s) {
        "use strict";
        s("9908")
    }, "4c1d": function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAB3UlEQVR4Xu3YO1LDMBCA4ZXHJ6CloSYFdLTcwGqcmjNwAXIDjkAfFzv2ESgY6hQUOQBXoPGDCUPBMPFDtlbSrpXalvV/llOsgpX/1Mr7IQLEE7BygfgJrPwAxD9Bb5+A1voeAK6UUm+IePR1Er0AaK13Sqmn3+hjmqa3RVF8+UBwDvAv/qe5bdtNWZYf4gHOxQNAgYhbH/GnZzo7ASHGOwMINd4JQMjx5AChx5MCcIgnA+ASTwLAKd46ALd4qwAc460BcI23AsA5fjEA9/hFABLiZwNIiZ8FICneGEBavBGAxPjJAFLjJwH0xXdd52WGZzI667puPzZrHByJZVl2lyTJu8lDA7t2dOI8CKC1flBKvQQWZbSdpmluqqo69N00CJDn+UVd168AsDF6ajgXPyPi49B2RqfCWZZdJ0myP4OwRcQinNZ5OxkFOC0rGWESgGSEyQBSEYwAJCIYA0hDmAUgCWE2gBSERQASEBYDcEewAsAZwRoAVwSrABwRrANwQyAB4IRABsAFgRSAAwI5QOgITgBCRnAGMIDwiYiX8wZay+9yCtCHMDa5XZ7Zv4JzgD8IOwDIAWB0cisOgDLIdG0vJ8B0k5TXRwBKXQ5rxxPA4S1R7jGeAEpdDmt/A/3NBlBGZo9SAAAAAElFTkSuQmCC"
    }, "4c3e": function (e, t, s) {
        "use strict";
        s("3918")
    }, "4cb9": function (e, t, s) {
        "use strict";
        s("742a")
    }, "4d06": function (e, t, s) {
        "use strict";
        s("0c24")
    }, "4eff": function (e, t, s) {
    }, 5334: function (e, t, s) {
    }, "53b9": function (e, t, s) {
    }, 5406: function (e, t, s) {
    }, "56d7": function (e, t, s) {
        "use strict";
        s.r(t);
        s("06f1"), s("450d");
        var i = s("6ac9"), n = s.n(i), r = (s("e3ea"), s("7bc3")), o = s.n(r), a = (s("d4df"), s("7fc1")), c = s.n(a),
            l = (s("560b"), s("dcdc")), u = s.n(l), m = (s("bd49"), s("18ff")), p = s.n(m), h = (s("960d"), s("defb")),
            f = s.n(h), d = (s("cb70"), s("b370")), g = s.n(d), v = (s("a7cc"), s("df33")), b = s.n(v),
            C = (s("be4f"), s("896a")), y = s.n(C), O = (s("10cb"), s("f3ad")), w = s.n(O), I = (s("f4f9"), s("c2cc")),
            A = s.n(I), M = (s("7a0f"), s("0f6c")), P = s.n(M), T = (s("1951"), s("eedf")), E = s.n(T),
            D = (s("9e1f"), s("6ed5")), _ = s.n(D), j = (s("cadf"), s("551c"), s("f751"), s("097d"), s("2b0e")),
            L = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "avatar",
                    class: "circle" === e.shape ? "shape-circle" : ""
                }, [s("img", {attrs: {src: e.avatarSrc}})])
            }, S = [], k = s("f6e3"), x = s.n(k), R = {
                props: {src: String, type: {type: String, default: "C2C"}, shape: {type: String, default: "circle"}},
                computed: {
                    avatarSrc: function () {
                        var e = this.src;
                        return /^(https:|http:|\/\/)/.test(e) ? e : this.defaultSrc
                    }, defaultSrc: function () {
                        switch (this.type) {
                            case"C2C":
                                return "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png";
                            case"GROUP":
                                return "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-3.png";
                            case this.TIM.TYPES.CONV_SYSTEM:
                                return x.a;
                            default:
                                return "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-1.png"
                        }
                    }
                }
            }, N = R, G = (s("691c"), s("2877")), U = Object(G["a"])(N, L, S, !1, null, "bd1b31fe", null), V = U.exports,
            B = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "container"}, [e.isLogin ? s("div", {
                    directives: [{
                        name: "loading",
                        rawName: "v-loading",
                        value: e.showLoading,
                        expression: "showLoading"
                    }],
                    staticClass: "loading",
                    attrs: {"element-loading-text": "正在拼命初始化...", "element-loading-background": "rgba(0, 0, 0, 0.8)"}
                }, [s("div", {staticClass: "chat-wrapper"}, [s("el-row", [s("el-col", {
                    attrs: {
                        xs: 10,
                        sm: 10,
                        md: 8,
                        lg: 8,
                        xl: 7
                    }
                }, [s("side-bar")], 1), s("el-col", {
                    attrs: {
                        xs: 14,
                        sm: 14,
                        md: 16,
                        lg: 16,
                        xl: 17
                    }
                }, [s("current-conversation")], 1)], 1), s("a", {
                    staticClass: "official-link",
                    attrs: {href: "https://cloud.tencent.com/product/im", target: "_blank"},
                    on: {click: e.handleLinkClick}
                }, [e._v("登录 即时通信IM 官网，了解更多体验方式")])], 1), s("calling", {
                    ref: "callLayer",
                    staticClass: "chat-wrapper"
                }), s("image-previewer"), s("group-live")], 1) : s("div", {attrs: {id: "wrapper"}}, [s("login"), s("qr-code-list")], 1), s("div", {staticClass: "bg"})])
            }, Y = [], $ = (s("8e6e"), s("456d"), s("c5f6"), s("ac6a"), s("46a1"), s("e5f2")), F = s.n($),
            q = (s("7f7f"), s("bd86")), H = s("2f62"), z = function () {
                var e = this, t = e.$createElement, i = e._self._c || t;
                return i("div", {staticClass: "current-conversation-wrapper"}, [e.showCurrentConversation ? i("div", {
                    staticClass: "current-conversation",
                    on: {scroll: e.onScroll}
                }, [i("div", {staticClass: "header"}, [i("div", {staticClass: "name"}, [e._v(e._s(e.name))]), i("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: !e.currentConversation.conversationID.includes("SYSTEM"),
                        expression: "!currentConversation.conversationID.includes('SYSTEM')"
                    }],
                    staticClass: "btn-more-info",
                    class: e.showConversationProfile ? "" : "left-arrow",
                    attrs: {title: "查看详细信息"},
                    on: {click: e.showMore}
                })]), i("div", {staticClass: "content"}, [i("div", {
                    ref: "message-list",
                    staticClass: "message-list",
                    on: {scroll: this.onScroll}
                }, [e.isCompleted ? i("div", {staticClass: "no-more"}, [e._v("没有更多了")]) : i("div", {staticClass: "more"}, [i("el-button", {
                    attrs: {type: "text"},
                    on: {
                        click: function (t) {
                            return e.$store.dispatch("getMessageList", e.currentConversation.conversationID)
                        }
                    }
                }, [e._v("查看更多")])], 1), e.selectMessage ? i("el-checkbox-group", {
                    model: {
                        value: e.checkList,
                        callback: function (t) {
                            e.checkList = t
                        },
                        expression: "checkList"
                    }
                }, e._l(e.currentMessageList, (function (e) {
                    return i("el-checkbox", {
                        key: e.ID,
                        attrs: {label: e.ID, disabled: "fail" === e.status}
                    }, [i("message-item", {attrs: {message: e}})], 1)
                })), 1) : e._l(e.currentMessageList, (function (e) {
                    return i("message-item", {key: e.ID, attrs: {message: e}})
                }))], 2), i("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.isShowScrollButtomTips,
                        expression: "isShowScrollButtomTips"
                    }], staticClass: "newMessageTips", on: {click: e.scrollMessageListToButtom}
                }, [e._v("回到最新位置")])]), e.showMessageSendBox ? i("div", {staticClass: "footer"}, [e.selectMessage ? i("div", {staticClass: "merger-btn"}, [i("div", {
                    staticClass: "relay-btn",
                    on: {click: e.singleRelay}
                }, [i("img", {
                    staticClass: "relay-icon",
                    attrs: {src: s("a2ef")}
                }), i("span", {staticClass: "relay-title"}, [e._v("逐条转发")])]), i("div", {
                    staticClass: "relay-btn",
                    on: {click: e.mergerRelay}
                }, [i("img", {
                    staticClass: "relay-icon",
                    attrs: {src: s("7360")}
                }), i("span", {staticClass: "relay-title"}, [e._v("合并转发")])]), i("div", {
                    staticClass: "relay-btn",
                    on: {click: e.closeSelectMessage}
                }, [i("img", {
                    staticClass: "relay-icon",
                    attrs: {src: s("c0cf")}
                }), i("span", {staticClass: "relay-title"}, [e._v("取消")])])]) : i("message-send-box")], 1) : e._e()]) : e._e(), i("div", [e.isShowConversationList ? i("message-relay") : e._e()], 1), e.showConversationProfile ? i("div", {staticClass: "profile"}, [i("conversation-profile")], 1) : e._e(), i("member-profile-card"), i("el-popover", {
                    ref: "dropdown",
                    attrs: {placement: "left-start", width: "700"},
                    model: {
                        value: e.mergerMessagePop, callback: function (t) {
                            e.mergerMessagePop = t
                        }, expression: "mergerMessagePop"
                    }
                }, [i("div", {staticClass: "pop-header"}, [e.mergerMessageList.length > 1 ? i("img", {
                    staticClass: "pop-back",
                    attrs: {src: s("4c1d")},
                    on: {click: e.mergerMessageBack}
                }) : e._e(), i("span", {staticClass: "title"}, [e._v(e._s(e.mergerTitle))]), i("img", {
                    staticClass: "pop-close",
                    attrs: {src: s("0c23")},
                    on: {click: e.closeMessagePop}
                })]), i("transition", {
                    attrs: {
                        name: "custom-classes-transition",
                        "enter-active-class": "animated fadeIn",
                        "leave-active-class": "animated fadeOut"
                    }
                }, [e.mergerMessagePop ? i("message-merger") : e._e()], 1)], 1)], 1)
            }, Q = [], J = (s("7514"), function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    style: e.focus ? {backgroundColor: "white"} : {},
                    attrs: {id: "message-send-box-wrapper"},
                    on: {drop: e.dropHandler}
                }, [s("div", {staticClass: "send-header-bar"}, [s("el-popover", {
                    attrs: {
                        placement: "top",
                        width: "400",
                        trigger: "click"
                    }
                }, [s("div", {staticClass: "emojis"}, e._l(e.emojiName, (function (t) {
                    return s("div", {
                        key: t, staticClass: "emoji", on: {
                            click: function (s) {
                                return e.chooseEmoji(t)
                            }
                        }
                    }, [s("img", {staticStyle: {width: "30px", height: "30px"}, attrs: {src: e.emojiUrl + e.emojiMap[t]}})])
                })), 0), s("i", {
                    staticClass: "iconfont icon-smile",
                    attrs: {slot: "reference", title: "发表情"},
                    slot: "reference"
                })]), s("i", {
                    staticClass: "iconfont icon-tupian",
                    attrs: {title: "发图片"},
                    on: {click: e.handleSendImageClick}
                }), s("i", {
                    staticClass: "el-icon-camera",
                    attrs: {title: "发视频"},
                    on: {click: e.handleSendVideoClick}
                }), s("i", {
                    staticClass: "iconfont icon-wenjian",
                    attrs: {title: "发文件"},
                    on: {click: e.handleSendFileClick}
                }), s("i", {
                    staticClass: "iconfont icon-zidingyi", attrs: {title: "发自定义消息"}, on: {
                        click: function (t) {
                            e.sendCustomDialogVisible = !0
                        }
                    }
                }), s("i", {
                    staticClass: "iconfont icon-diaocha", attrs: {title: "小调查"}, on: {
                        click: function (t) {
                            e.surveyDialogVisible = !0
                        }
                    }
                }), s("el-dropdown", [s("span", {staticClass: "el-dropdown-link"}, [e.toAccount !== e.userID ? s("i", {
                    staticClass: "el-icon-phone-outline",
                    attrs: {title: "语音通话"}
                }) : e._e()]), s("el-dropdown-menu", {
                    attrs: {slot: "dropdown"},
                    slot: "dropdown"
                }, [s("el-dropdown-item", {
                    nativeOn: {
                        click: function (t) {
                            return e.trtcCalling("video")
                        }
                    }
                }, [e._v("视频通话")]), s("el-dropdown-item", {
                    nativeOn: {
                        click: function (t) {
                            return e.trtcCalling("audio")
                        }
                    }
                }, [e._v("语音通话")])], 1)], 1), e.currentConversationType === e.TIM.TYPES.CONV_GROUP && "AVChatRoom" !== e.groupProfile.type ? s("div", {
                    staticClass: "group-live-icon-box",
                    attrs: {title: "群直播"},
                    on: {click: e.groupLive}
                }, [s("i", {staticClass: "group-live-icon"}), s("i", {staticClass: "group-live-icon-hover"})]) : e._e()], 1), s("el-dialog", {
                    attrs: {
                        title: "发自定义消息",
                        visible: e.sendCustomDialogVisible,
                        width: "30%"
                    }, on: {
                        "update:visible": function (t) {
                            e.sendCustomDialogVisible = t
                        }
                    }
                }, [s("el-form", {attrs: {"label-width": "100px"}}, [s("el-form-item", {attrs: {label: "data"}}, [s("el-input", {
                    model: {
                        value: e.form.data,
                        callback: function (t) {
                            e.$set(e.form, "data", t)
                        },
                        expression: "form.data"
                    }
                })], 1), s("el-form-item", {attrs: {label: "description"}}, [s("el-input", {
                    model: {
                        value: e.form.description,
                        callback: function (t) {
                            e.$set(e.form, "description", t)
                        },
                        expression: "form.description"
                    }
                })], 1), s("el-form-item", {attrs: {label: "extension"}}, [s("el-input", {
                    model: {
                        value: e.form.extension,
                        callback: function (t) {
                            e.$set(e.form, "extension", t)
                        },
                        expression: "form.extension"
                    }
                })], 1)], 1), s("span", {
                    staticClass: "dialog-footer",
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    on: {
                        click: function (t) {
                            e.sendCustomDialogVisible = !1
                        }
                    }
                }, [e._v("取 消")]), s("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.sendCustomMessage}
                }, [e._v("确 定")])], 1)], 1), s("el-dialog", {
                    attrs: {
                        title: "对IM Web demo的建议和使用感受",
                        visible: e.surveyDialogVisible,
                        width: "30%"
                    }, on: {
                        "update:visible": function (t) {
                            e.surveyDialogVisible = t
                        }
                    }
                }, [s("el-form", {attrs: {"label-width": "100px"}}, [s("el-form-item", {attrs: {label: "评分"}}, [s("div", {staticClass: "block"}, [s("el-rate", {
                    attrs: {
                        colors: e.colors,
                        "show-text": ""
                    }, model: {
                        value: e.rate, callback: function (t) {
                            e.rate = t
                        }, expression: "rate"
                    }
                })], 1)]), s("el-form-item", {attrs: {label: "建议"}}, [s("el-input", {
                    attrs: {
                        type: "textarea",
                        rows: 2,
                        placeholder: "请输入内容",
                        resize: "none"
                    }, model: {
                        value: e.suggestion, callback: function (t) {
                            e.suggestion = t
                        }, expression: "suggestion"
                    }
                })], 1)], 1), s("span", {
                    staticClass: "dialog-footer",
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    on: {
                        click: function (t) {
                            e.surveyDialogVisible = !1
                        }
                    }
                }, [e._v("取 消")]), s("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.sendSurvey}
                }, [e._v("确 定")])], 1)], 1), s("div", {staticClass: "bottom"}, [s("textarea", {
                    directives: [{
                        name: "model",
                        rawName: "v-model",
                        value: e.messageContent,
                        expression: "messageContent"
                    }],
                    ref: "text-input",
                    staticClass: "text-input",
                    attrs: {rows: "4", resize: "false"},
                    domProps: {value: e.messageContent},
                    on: {
                        focus: function (t) {
                            e.focus = !0
                        }, blur: function (t) {
                            e.focus = !1
                        }, input: [function (t) {
                            t.target.composing || (e.messageContent = t.target.value)
                        }, e.inputChange], keydown: [function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") || t.ctrlKey || t.shiftKey || t.altKey || t.metaKey ? null : (t.preventDefault(), e.handleEnter(t))
                        }, function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "up", 38, t.key, ["Up", "ArrowUp"]) ? null : (t.stopPropagation(), e.handleUp(t))
                        }, function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "down", 40, t.key, ["Down", "ArrowDown"]) ? null : (t.stopPropagation(), e.handleDown(t))
                        }], keyup: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : t.ctrlKey ? (t.preventDefault(), t.shiftKey || t.altKey || t.metaKey ? null : e.handleLine(t)) : null
                        }
                    }
                }), s("el-tooltip", {
                    staticClass: "item",
                    attrs: {effect: "dark", content: "按Enter发送消息，Ctrl+Enter换行", placement: "left-start"}
                }, [s("div", {
                    staticClass: "btn-send",
                    on: {click: e.sendTextMessage}
                }, [s("div", {staticClass: "tim-icon-send"})])])], 1), s("input", {
                    ref: "imagePicker",
                    staticStyle: {display: "none"},
                    attrs: {type: "file", id: "imagePicker", accept: ".jpg, .jpeg, .png, .gif, .bmp"},
                    on: {change: e.sendImage}
                }), s("input", {
                    ref: "filePicker",
                    staticStyle: {display: "none"},
                    attrs: {type: "file", id: "filePicker"},
                    on: {change: e.sendFile}
                }), s("input", {
                    ref: "videoPicker",
                    staticStyle: {display: "none"},
                    attrs: {type: "file", id: "videoPicker", accept: ".mp4"},
                    on: {change: e.sendVideo}
                }), e.currentConversationType === e.TIM.TYPES.CONV_GROUP && e.showCallingMember ? s("div", {staticClass: "calling-member-list"}, [s("calling-member-list", {
                    attrs: {type: e.listTpye},
                    on: {getList: e.getList}
                }), s("div", {staticClass: "calling-list-btn"}, [s("span", {
                    staticClass: "calling-btn",
                    on: {click: e.cancelCalling}
                }, [e._v("取消")]), s("span", {
                    staticClass: "calling-btn",
                    on: {click: e.callingHandler}
                }, [e._v("确定")])])], 1) : e._e()], 1)
            }), K = [], Z = (s("20d6"), s("78a7"), s("33ca")), X = s.n(Z), W = (s("0c67"), s("299c")), ee = s.n(W),
            te = (s("eca7"), s("3787")), se = s.n(te), ie = (s("425f"), s("4105")), ne = s.n(ie), re = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "group-member-list-wrapper"}, [s("div", {staticClass: "header"}, [s("span", {staticClass: "member-count text-ellipsis"}, [e._v("群成员：" + e._s(e.currentConversation.groupProfile.memberCount))])]), s("div", {staticClass: "scroll-content"}, [s("div", {staticClass: "group-member-list"}, [s("el-checkbox-group", {
                    on: {change: e.handleCheckedMembersChange},
                    model: {
                        value: e.callingList, callback: function (t) {
                            e.callingList = t
                        }, expression: "callingList"
                    }
                }, ["groupAt" === e.type ? s("el-checkbox", {
                    attrs: {
                        label: JSON.stringify({
                            userID: this.TIM.TYPES.MSG_AT_ALL,
                            nick: "所有人"
                        })
                    }
                }, [s("div", {staticClass: "group-member"}, [s("avatar", {attrs: {src: ""}}), s("div", {staticClass: "member-name text-ellipsis"}, [s("span", [e._v("所有人")])])], 1)]) : e._e(), e._l(e.members, (function (t) {
                    return s("el-checkbox", {
                        key: t.userID,
                        attrs: {
                            disabled: t.userID === e.userID,
                            label: JSON.stringify({userID: t.userID, nick: t.nameCard || t.nick || t.userID})
                        }
                    }, [s("div", {staticClass: "group-member"}, [s("avatar", {attrs: {src: t.avatar}}), s("div", {staticClass: "member-name text-ellipsis"}, [t.nameCard ? s("span", [e._v(e._s(t.nameCard))]) : t.nick ? s("span", [e._v(e._s(t.nick))]) : s("span", [e._v(e._s(t.userID))])])], 1)])
                }))], 2)], 1)]), s("div", {staticClass: "more"}, [e.showLoadMore ? s("el-button", {
                    attrs: {type: "text"},
                    on: {click: e.loadMore}
                }, [e._v("查看更多")]) : e._e()], 1)])
            }, oe = [];

        function ae(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function ce(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? ae(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : ae(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var le = {
                props: ["type"], data: function () {
                    return {callingList: [], addGroupMemberVisible: !1, currentMemberID: "", count: 30}
                }, components: {}, computed: ce(ce({}, Object(H["c"])({
                    userID: function (e) {
                        return e.user.userID
                    }, currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentMemberList: function (e) {
                        return e.group.currentMemberList
                    }
                })), {}, {
                    showLoadMore: function () {
                        return this.members.length < this.currentConversation.groupProfile.memberCount
                    }, members: function () {
                        return this.currentMemberList.slice(0, this.count)
                    }
                }), methods: {
                    handleCheckedMembersChange: function () {
                        this.$emit("getList", this.callingList)
                    }, getGroupMemberAvatarText: function (e) {
                        switch (e) {
                            case"Owner":
                                return "群主";
                            case"Admin":
                                return "管理员";
                            default:
                                return "群成员"
                        }
                    }, loadMore: function () {
                        var e = this;
                        this.$store.dispatch("getGroupMemberList", this.groupProfile.groupID).then((function () {
                            e.count += 30
                        }))
                    }
                }
            }, ue = le, me = (s("e545"), Object(G["a"])(ue, re, oe, !1, null, "6ee3a287", null)), pe = me.exports,
            he = "https://imgcache.qq.com/open/qcloud/tim/assets/emoji/", fe = {
                "[NO]": "emoji_0@2x.png",
                "[OK]": "emoji_1@2x.png",
                "[下雨]": "emoji_2@2x.png",
                "[么么哒]": "emoji_3@2x.png",
                "[乒乓]": "emoji_4@2x.png",
                "[便便]": "emoji_5@2x.png",
                "[信封]": "emoji_6@2x.png",
                "[偷笑]": "emoji_7@2x.png",
                "[傲慢]": "emoji_8@2x.png",
                "[再见]": "emoji_9@2x.png",
                "[冷汗]": "emoji_10@2x.png",
                "[凋谢]": "emoji_11@2x.png",
                "[刀]": "emoji_12@2x.png",
                "[删除]": "emoji_13@2x.png",
                "[勾引]": "emoji_14@2x.png",
                "[发呆]": "emoji_15@2x.png",
                "[发抖]": "emoji_16@2x.png",
                "[可怜]": "emoji_17@2x.png",
                "[可爱]": "emoji_18@2x.png",
                "[右哼哼]": "emoji_19@2x.png",
                "[右太极]": "emoji_20@2x.png",
                "[右车头]": "emoji_21@2x.png",
                "[吐]": "emoji_22@2x.png",
                "[吓]": "emoji_23@2x.png",
                "[咒骂]": "emoji_24@2x.png",
                "[咖啡]": "emoji_25@2x.png",
                "[啤酒]": "emoji_26@2x.png",
                "[嘘]": "emoji_27@2x.png",
                "[回头]": "emoji_28@2x.png",
                "[困]": "emoji_29@2x.png",
                "[坏笑]": "emoji_30@2x.png",
                "[多云]": "emoji_31@2x.png",
                "[大兵]": "emoji_32@2x.png",
                "[大哭]": "emoji_33@2x.png",
                "[太阳]": "emoji_34@2x.png",
                "[奋斗]": "emoji_35@2x.png",
                "[奶瓶]": "emoji_36@2x.png",
                "[委屈]": "emoji_37@2x.png",
                "[害羞]": "emoji_38@2x.png",
                "[尴尬]": "emoji_39@2x.png",
                "[左哼哼]": "emoji_40@2x.png",
                "[左太极]": "emoji_41@2x.png",
                "[左车头]": "emoji_42@2x.png",
                "[差劲]": "emoji_43@2x.png",
                "[弱]": "emoji_44@2x.png",
                "[强]": "emoji_45@2x.png",
                "[彩带]": "emoji_46@2x.png",
                "[彩球]": "emoji_47@2x.png",
                "[得意]": "emoji_48@2x.png",
                "[微笑]": "emoji_49@2x.png",
                "[心碎了]": "emoji_50@2x.png",
                "[快哭了]": "emoji_51@2x.png",
                "[怄火]": "emoji_52@2x.png",
                "[怒]": "emoji_53@2x.png",
                "[惊恐]": "emoji_54@2x.png",
                "[惊讶]": "emoji_55@2x.png",
                "[憨笑]": "emoji_56@2x.png",
                "[手枪]": "emoji_57@2x.png",
                "[打哈欠]": "emoji_58@2x.png",
                "[抓狂]": "emoji_59@2x.png",
                "[折磨]": "emoji_60@2x.png",
                "[抠鼻]": "emoji_61@2x.png",
                "[抱抱]": "emoji_62@2x.png",
                "[抱拳]": "emoji_63@2x.png",
                "[拳头]": "emoji_64@2x.png",
                "[挥手]": "emoji_65@2x.png",
                "[握手]": "emoji_66@2x.png",
                "[撇嘴]": "emoji_67@2x.png",
                "[擦汗]": "emoji_68@2x.png",
                "[敲打]": "emoji_69@2x.png",
                "[晕]": "emoji_70@2x.png",
                "[月亮]": "emoji_71@2x.png",
                "[棒棒糖]": "emoji_72@2x.png",
                "[汽车]": "emoji_73@2x.png",
                "[沙发]": "emoji_74@2x.png",
                "[流汗]": "emoji_75@2x.png",
                "[流泪]": "emoji_76@2x.png",
                "[激动]": "emoji_77@2x.png",
                "[灯泡]": "emoji_78@2x.png",
                "[炸弹]": "emoji_79@2x.png",
                "[熊猫]": "emoji_80@2x.png",
                "[爆筋]": "emoji_81@2x.png",
                "[爱你]": "emoji_82@2x.png",
                "[爱心]": "emoji_83@2x.png",
                "[爱情]": "emoji_84@2x.png",
                "[猪头]": "emoji_85@2x.png",
                "[猫咪]": "emoji_86@2x.png",
                "[献吻]": "emoji_87@2x.png",
                "[玫瑰]": "emoji_88@2x.png",
                "[瓢虫]": "emoji_89@2x.png",
                "[疑问]": "emoji_90@2x.png",
                "[白眼]": "emoji_91@2x.png",
                "[皮球]": "emoji_92@2x.png",
                "[睡觉]": "emoji_93@2x.png",
                "[磕头]": "emoji_94@2x.png",
                "[示爱]": "emoji_95@2x.png",
                "[礼品袋]": "emoji_96@2x.png",
                "[礼物]": "emoji_97@2x.png",
                "[篮球]": "emoji_98@2x.png",
                "[米饭]": "emoji_99@2x.png",
                "[糗大了]": "emoji_100@2x.png",
                "[红双喜]": "emoji_101@2x.png",
                "[红灯笼]": "emoji_102@2x.png",
                "[纸巾]": "emoji_103@2x.png",
                "[胜利]": "emoji_104@2x.png",
                "[色]": "emoji_105@2x.png",
                "[药]": "emoji_106@2x.png",
                "[菜刀]": "emoji_107@2x.png",
                "[蛋糕]": "emoji_108@2x.png",
                "[蜡烛]": "emoji_109@2x.png",
                "[街舞]": "emoji_110@2x.png",
                "[衰]": "emoji_111@2x.png",
                "[西瓜]": "emoji_112@2x.png",
                "[调皮]": "emoji_113@2x.png",
                "[象棋]": "emoji_114@2x.png",
                "[跳绳]": "emoji_115@2x.png",
                "[跳跳]": "emoji_116@2x.png",
                "[车厢]": "emoji_117@2x.png",
                "[转圈]": "emoji_118@2x.png",
                "[鄙视]": "emoji_119@2x.png",
                "[酷]": "emoji_120@2x.png",
                "[钞票]": "emoji_121@2x.png",
                "[钻戒]": "emoji_122@2x.png",
                "[闪电]": "emoji_123@2x.png",
                "[闭嘴]": "emoji_124@2x.png",
                "[闹钟]": "emoji_125@2x.png",
                "[阴险]": "emoji_126@2x.png",
                "[难过]": "emoji_127@2x.png",
                "[雨伞]": "emoji_128@2x.png",
                "[青蛙]": "emoji_129@2x.png",
                "[面条]": "emoji_130@2x.png",
                "[鞭炮]": "emoji_131@2x.png",
                "[风车]": "emoji_132@2x.png",
                "[飞吻]": "emoji_133@2x.png",
                "[飞机]": "emoji_134@2x.png",
                "[饥饿]": "emoji_135@2x.png",
                "[香蕉]": "emoji_136@2x.png",
                "[骷髅]": "emoji_137@2x.png",
                "[麦克风]": "emoji_138@2x.png",
                "[麻将]": "emoji_139@2x.png",
                "[鼓掌]": "emoji_140@2x.png",
                "[龇牙]": "emoji_141@2x.png"
            },
            de = ["[龇牙]", "[调皮]", "[流汗]", "[偷笑]", "[再见]", "[敲打]", "[擦汗]", "[猪头]", "[玫瑰]", "[流泪]", "[大哭]", "[嘘]", "[酷]", "[抓狂]", "[委屈]", "[便便]", "[炸弹]", "[菜刀]", "[可爱]", "[色]", "[害羞]", "[得意]", "[吐]", "[微笑]", "[怒]", "[尴尬]", "[惊恐]", "[冷汗]", "[爱心]", "[示爱]", "[白眼]", "[傲慢]", "[难过]", "[惊讶]", "[疑问]", "[困]", "[么么哒]", "[憨笑]", "[爱情]", "[衰]", "[撇嘴]", "[阴险]", "[奋斗]", "[发呆]", "[右哼哼]", "[抱抱]", "[坏笑]", "[飞吻]", "[鄙视]", "[晕]", "[大兵]", "[可怜]", "[强]", "[弱]", "[握手]", "[胜利]", "[抱拳]", "[凋谢]", "[米饭]", "[蛋糕]", "[西瓜]", "[啤酒]", "[瓢虫]", "[勾引]", "[OK]", "[爱你]", "[咖啡]", "[月亮]", "[刀]", "[发抖]", "[差劲]", "[拳头]", "[心碎了]", "[太阳]", "[礼物]", "[皮球]", "[骷髅]", "[挥手]", "[闪电]", "[饥饿]", "[咒骂]", "[折磨]", "[抠鼻]", "[鼓掌]", "[糗大了]", "[左哼哼]", "[打哈欠]", "[快哭了]", "[吓]", "[篮球]", "[乒乓]", "[NO]", "[跳跳]", "[怄火]", "[转圈]", "[磕头]", "[回头]", "[跳绳]", "[激动]", "[街舞]", "[献吻]", "[左太极]", "[右太极]", "[闭嘴]", "[猫咪]", "[红双喜]", "[鞭炮]", "[红灯笼]", "[麻将]", "[麦克风]", "[礼品袋]", "[信封]", "[象棋]", "[彩带]", "[蜡烛]", "[爆筋]", "[棒棒糖]", "[奶瓶]", "[面条]", "[香蕉]", "[飞机]", "[左车头]", "[车厢]", "[右车头]", "[多云]", "[下雨]", "[钞票]", "[熊猫]", "[灯泡]", "[风车]", "[闹钟]", "[雨伞]", "[彩球]", "[钻戒]", "[沙发]", "[纸巾]", "[手枪]", "[青蛙]"];

        function ge(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function ve(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? ge(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : ge(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var be = {
                name: "message-send-box",
                props: ["scrollMessageListToButtom"],
                components: {
                    callingMemberList: pe,
                    ElInput: w.a,
                    ElForm: ne.a,
                    ElFormItem: se.a,
                    ElDialog: b.a,
                    ElPopover: n.a,
                    ElTooltip: ee.a,
                    ElRate: X.a
                },
                data: function () {
                    return {
                        callingList: [],
                        groupAtList: [],
                        listTpye: "",
                        callingType: "",
                        groupAt: !1,
                        showCallingMember: !1,
                        colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
                        messageContent: "",
                        isSendCustomMessage: !1,
                        sendCustomDialogVisible: !1,
                        surveyDialogVisible: !1,
                        form: {data: "", description: "", extension: ""},
                        rate: 5,
                        suggestion: "",
                        file: "",
                        emojiMap: fe,
                        emojiName: de,
                        emojiUrl: he,
                        showAtGroupMember: !1,
                        atUserID: "",
                        focus: !1
                    }
                },
                computed: ve(ve({}, Object(H["b"])(["toAccount", "currentConversationType"])), Object(H["c"])({
                    memberList: function (e) {
                        return e.group.currentMemberList
                    }, userID: function (e) {
                        return e.user.userID
                    }, groupProfile: function (e) {
                        return e.conversation.currentConversation.groupProfile
                    }
                })),
                mounted: function () {
                    this.$refs["text-input"].addEventListener("paste", this.handlePaste), this.$bus.$on("reEditMessage", this.reEditMessage)
                },
                beforeDestroy: function () {
                    this.$refs["text-input"].removeEventListener("paste", this.handlePaste)
                },
                methods: {
                    getList: function (e) {
                        this.callingList = e.map((function (e) {
                            var t = JSON.parse(e);
                            return t.userID
                        })), this.groupAtList = e.map((function (e) {
                            var t = JSON.parse(e);
                            return t.nick
                        }))
                    }, cancelCalling: function () {
                        this.showCallingMember = !1
                    }, callingHandler: function () {
                        var e = this;
                        if (this.callingList.length < 1) this.$store.commit("showMessage", {
                            type: "warning",
                            message: "请选择成员"
                        }); else {
                            if ("groupAt" === this.listTpye) return this.groupAtList.forEach((function (t, s) {
                                e.messageContent += 0 === s ? "".concat(t, " ") : "@".concat(t, " ")
                            })), this.showCallingMember = !1, void this.$refs["text-input"].focus();
                            if ("calling" === this.listTpye) {
                                var t = {memberList: this.callingList, type: this.TIM.TYPES.CONV_GROUP};
                                this.$store.commit("setCallingList", t), "video" === this.callingType && this.$bus.$emit("video-call"), "audio" === this.callingType && this.$bus.$emit("audio-call"), this.showCallingMember = !1
                            }
                        }
                    }, trtcCalling: function (e) {
                        if (this.listTpye = "calling", "video" === e && (this.callingType = "video"), "audio" === e && (this.callingType = "audio"), "C2C" === this.currentConversationType) {
                            var t = [this.toAccount], s = {memberList: t, type: "C2C"};
                            return this.$store.commit("setCallingList", s), void this.$bus.$emit("".concat(e, "-call"))
                        }
                        this.currentConversationType === this.TIM.TYPES.CONV_GROUP && (this.showCallingMember = !0)
                    }, handleEmojiShow: function () {
                        this.emojiShow = !0, this.bigEmojiShow = !1
                    }, handleBigEmojiShow: function (e) {
                        var t = document.getElementById("bigEmojiBox");
                        t && (t.scrollTop = 0), this.curItemIndex = e, this.curBigEmojiItemList = this.bigEmojiList[e].list, this.emojiShow = !1, this.bigEmojiShow = !0
                    }, chooseBigEmoji: function (e) {
                        var t = this;
                        this.popoverVisible = !1;
                        var s = this.tim.createFaceMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {index: this.curItemIndex + 1, data: "".concat(e, "@2x")}
                        });
                        this.$store.commit("pushCurrentMessageList", s), this.$bus.$emit("scroll-bottom"), this.tim.sendMessage(s).catch((function (e) {
                            t.$store.commit("showMessage", {type: "error", message: e.message})
                        }))
                    }, reEditMessage: function (e) {
                        this.messageContent = e
                    }, handleUp: function () {
                        var e = this, t = this.memberList.findIndex((function (t) {
                            return t.userID === e.atUserID
                        }));
                        t - 1 < 0 || (this.atUserID = this.memberList[t - 1].userID)
                    }, handleDown: function () {
                        var e = this, t = this.memberList.findIndex((function (t) {
                            return t.userID === e.atUserID
                        }));
                        t + 1 >= this.memberList.length || (this.atUserID = this.memberList[t + 1].userID)
                    }, handleEnter: function () {
                        this.sendTextMessage()
                    }, inputChange: function (e) {
                        this.currentConversationType === this.TIM.TYPES.CONV_GROUP && "@" === e.data && (this.groupAt = !0, this.listTpye = "groupAt", this.showCallingMember = !0), " " === e.data && -1 !== this.messageContent.indexOf("@ ") && (this.groupAt = !1, this.listTpye = "", this.showCallingMember = !1)
                    }, handleLine: function () {
                        this.messageContent += "\n"
                    }, handlePaste: function (e) {
                        var t, s = this, i = e.clipboardData;
                        if (i && i.files && i.files.length > 0 && (t = i.files[0]), "undefined" !== typeof t) {
                            var n = this.tim.createImageMessage({
                                to: this.toAccount,
                                conversationType: this.currentConversationType,
                                payload: {file: t},
                                onProgress: function (e) {
                                    s.$set(n, "progress", e)
                                }
                            });
                            this.$store.commit("pushCurrentMessageList", n);
                            var r = this.tim.sendMessage(n);
                            r.catch((function (e) {
                                s.$store.commit("showMessage", {type: "error", message: e.message})
                            }))
                        }
                    }, dropHandler: function (e) {
                        var t = this;
                        e.preventDefault();
                        var s = e.dataTransfer.files[0], i = {};
                        i = "video/mp4" === s.type ? this.tim.createVideoMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {file: s},
                            onProgress: function (e) {
                                t.$set(i, "progress", e)
                            }
                        }) : this.tim.createFileMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {file: s},
                            onProgress: function (e) {
                                t.$set(i, "progress", e)
                            }
                        }), this.$store.commit("pushCurrentMessageList", i), this.tim.sendMessage(i).then((function () {
                            t.$refs.videoPicker.value = null
                        })).catch((function (e) {
                            t.$store.commit("showMessage", {message: e.message, type: "error"})
                        }))
                    }, sendTextMessage: function () {
                        var e = this;
                        if ("" === this.messageContent || 0 === this.messageContent.trim().length) return this.messageContent = "", void this.$store.commit("showMessage", {
                            message: "不能发送空消息哦！",
                            type: "info"
                        });
                        if (this.currentConversationType === this.TIM.TYPES.CONV_GROUP && this.groupAt) {
                            var t = this.tim.createTextAtMessage({
                                to: this.toAccount,
                                conversationType: this.TIM.TYPES.CONV_GROUP,
                                payload: {text: this.messageContent, atUserList: this.callingList}
                            });
                            return this.$store.commit("pushCurrentMessageList", t), this.$bus.$emit("scroll-bottom"), this.tim.sendMessage(t).catch((function (t) {
                                e.$store.commit("showMessage", {type: "error", message: t.message})
                            })), this.messageContent = "", void (this.groupAt = !1)
                        }
                        var s = this.tim.createTextMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {text: this.messageContent}
                        });
                        this.$store.commit("pushCurrentMessageList", s), this.$bus.$emit("scroll-bottom"), this.tim.sendMessage(s).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })), this.messageContent = ""
                    }, sendCustomMessage: function () {
                        var e = this;
                        if (0 !== this.form.data.length || 0 !== this.form.description.length || 0 !== this.form.extension.length) {
                            var t = this.tim.createCustomMessage({
                                to: this.toAccount,
                                conversationType: this.currentConversationType,
                                payload: {
                                    data: this.form.data,
                                    description: this.form.description,
                                    extension: this.form.extension
                                }
                            });
                            this.$store.commit("pushCurrentMessageList", t), this.tim.sendMessage(t).catch((function (t) {
                                e.$store.commit("showMessage", {type: "error", message: t.message})
                            })), Object.assign(this.form, {
                                data: "",
                                description: "",
                                extension: ""
                            }), this.sendCustomDialogVisible = !1
                        } else this.$store.commit("showMessage", {message: "不能发送空消息", type: "info"})
                    }, random: function (e, t) {
                        return Math.floor(Math.random() * (t - e + 1) + e)
                    }, sendSurvey: function () {
                        var e = this, t = this.tim.createCustomMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {data: "survey", description: String(this.rate), extension: this.suggestion}
                        });
                        this.$store.commit("pushCurrentMessageList", t), Object.assign(this.form, {
                            data: "",
                            description: "",
                            extension: ""
                        }), this.tim.sendMessage(t).then((function () {
                            Object.assign(e, {rate: 5, suggestion: ""})
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })), this.surveyDialogVisible = !1
                    }, chooseEmoji: function (e) {
                        this.messageContent += e
                    }, handleSendImageClick: function () {
                        this.$refs.imagePicker.click()
                    }, handleSendFileClick: function () {
                        this.$refs.filePicker.click()
                    }, handleSendVideoClick: function () {
                        this.$refs.videoPicker.click()
                    }, groupLive: function () {
                        this.$store.commit("updateGroupLiveInfo", {
                            groupID: this.toAccount,
                            anchorID: this.userID
                        }), this.$bus.$emit("open-group-live", {channel: 1})
                    }, sendImage: function () {
                        var e = this, t = this.tim.createImageMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {file: document.getElementById("imagePicker")},
                            onProgress: function (s) {
                                e.$set(t, "progress", s)
                            }
                        });
                        this.$store.commit("pushCurrentMessageList", t), this.tim.sendMessage(t).then((function () {
                            e.$refs.imagePicker.value = null
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        }))
                    }, sendFile: function () {
                        var e = this, t = this.tim.createFileMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {file: document.getElementById("filePicker")},
                            onProgress: function (s) {
                                e.$set(t, "progress", s)
                            }
                        });
                        this.$store.commit("pushCurrentMessageList", t), this.tim.sendMessage(t).then((function () {
                            e.$refs.filePicker.value = null
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        }))
                    }, sendVideo: function () {
                        var e = this, t = this.tim.createVideoMessage({
                            to: this.toAccount,
                            conversationType: this.currentConversationType,
                            payload: {file: document.getElementById("videoPicker")},
                            onProgress: function (s) {
                                e.$set(t, "progress", s)
                            }
                        });
                        this.$store.commit("pushCurrentMessageList", t), this.tim.sendMessage(t).then((function () {
                            e.$refs.videoPicker.value = null
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        }))
                    }
                }
            }, Ce = be, ye = (s("bb07"), Object(G["a"])(Ce, J, K, !1, null, "25aacfaa", null)), Oe = ye.exports,
            we = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "message-wrapper",
                    class: e.messagePosition
                }, [e.currentConversationType === e.TIM.TYPES.CONV_C2C ? s("div", {
                    staticClass: "c2c-layout",
                    class: e.messagePosition
                }, [e.showAvatar ? s("div", {staticClass: "col-1"}, [s("avatar", {attrs: {src: e.avatar}})], 1) : e._e(), s("div", {staticClass: "col-2"}, [s("div", {staticClass: "content-wrapper"}, [e.isMine ? s("message-status-icon", {attrs: {message: e.message}}) : e._e(), e.message.type === e.TIM.TYPES.MSG_TEXT ? s("text-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_IMAGE ? s("image-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_FILE ? s("file-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_SOUND ? s("sound-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_GRP_TIP ? s("group-tip-element", {
                    attrs: {
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_GRP_SYS_NOTICE ? s("group-system-notice-element", {
                    attrs: {
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_CUSTOM ? s("custom-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_FACE ? s("face-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_VIDEO ? s("video-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_GEO ? s("geo-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_MERGER ? s("merger-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : s("span", [e._v("暂未支持的消息类型：" + e._s(e.message.type))])], 1), e.showMessageHeader ? s("message-footer", {attrs: {message: e.message}}) : e._e()], 1), s("div", {staticClass: "col-3"})]) : e._e(), e.currentConversationType === e.TIM.TYPES.CONV_GROUP ? s("div", {
                    staticClass: "group-layout",
                    class: e.messagePosition
                }, [e.showAvatar ? s("div", {staticClass: "col-1"}, [s("avatar", {
                    staticClass: "group-member-avatar",
                    attrs: {src: e.avatar},
                    nativeOn: {
                        click: function (t) {
                            return e.showGroupMemberProfile(t)
                        }
                    }
                })], 1) : e._e(), s("div", {staticClass: "col-2"}, [e.showMessageHeader ? s("message-header", {attrs: {message: e.message}}) : e._e(), s("div", {staticClass: "content-wrapper"}, [e.isMine ? s("message-status-icon", {attrs: {message: e.message}}) : e._e(), e.message.type === e.TIM.TYPES.MSG_TEXT ? s("text-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_IMAGE ? s("image-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_FILE ? s("file-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_SOUND ? s("sound-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_GRP_TIP ? s("group-tip-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_CUSTOM ? s("custom-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_FACE ? s("face-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_VIDEO ? s("video-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_GEO ? s("geo-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : e.message.type === e.TIM.TYPES.MSG_MERGER ? s("merger-element", {
                    attrs: {
                        isMine: e.isMine,
                        payload: e.message.payload,
                        message: e.message
                    }
                }) : s("span", [e._v("暂未支持的消息类型：" + e._s(e.message.type))])], 1)], 1), s("div", {staticClass: "col-3"})]) : e._e(), e.currentConversationType === e.TIM.TYPES.CONV_SYSTEM ? s("div", {staticClass: "system-layout"}, [s("div", {staticClass: "col-1"}, [s("avatar", {
                    attrs: {
                        src: e.avatar,
                        type: e.currentConversationType
                    }
                })], 1), s("div", {staticClass: "col-2"}, [s("message-header", {attrs: {message: e.message}}), s("group-system-notice-element", {
                    attrs: {
                        payload: e.message.payload,
                        message: e.message
                    }
                })], 1)]) : e._e()])
            }, Ie = [], Ae = (s("6762"), s("2fdb"), function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    class: e.messageIconClass,
                    staticStyle: {width: "16px", height: "16px"},
                    on: {click: e.handleIconClick}
                }, [e._v(e._s("message-send-fail" === e.messageIconClass ? "!" : ""))])
            }), Me = [], Pe = {
                name: "MessageStatusIcon",
                props: {message: {type: Object, required: !0}},
                computed: {
                    messageIconClass: function () {
                        switch (this.message.status) {
                            case"unSend":
                                return "el-icon-loading";
                            case"fail":
                                return "message-send-fail";
                            default:
                                return ""
                        }
                    }
                },
                methods: {
                    handleIconClick: function () {
                        var e = this;
                        "message-send-fail" === this.messageIconClass && this.tim.resendMessage(this.message).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        }))
                    }
                }
            }, Te = Pe, Ee = (s("a86b"), Object(G["a"])(Te, Ae, Me, !1, null, "97210bde", null)), De = Ee.exports,
            _e = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "base",
                    class: [e.isMine ? "right" : "left"]
                }, [s("div", {staticClass: "name text-ellipsis"}, [e._v(e._s(e.from))]), s("div", {staticClass: "date"}, [e._v(e._s(e.date))])])
            }, je = [];

        function Le(e) {
            var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "-", s = e.getFullYear(),
                i = e.getMonth() + 1, n = e.getDate();
            return "".concat(s).concat(t).concat(Re(i)).concat(t).concat(Re(n))
        }

        function Se(e) {
            var t = arguments.length > 1 && void 0 !== arguments[1] && arguments[1], s = e.getHours(),
                i = e.getMinutes(), n = e.getSeconds();
            return t ? "".concat(Re(s), ":").concat(Re(i), ":").concat(Re(n)) : "".concat(s, ":").concat(Re(i))
        }

        function ke(e) {
            return "".concat(Le(e), " ").concat(Se(e))
        }

        function xe(e) {
            return e.toDateString() === (new Date).toDateString()
        }

        function Re(e) {
            return e < 10 ? "0".concat(e) : e
        }

        function Ne(e) {
            var t, s, i, n, r = e;
            return r >= 3600 ? (s = parseInt(r / 3600) < 10 ? "0" + parseInt(r / 3600) : parseInt(r / 3600), i = parseInt(r % 60 / 60) < 10 ? "0" + parseInt(r % 60 / 60) : parseInt(r % 60 / 60), n = r % 3600 < 10 ? "0" + r % 3600 : r % 3600, n > 60 && (i = parseInt(n / 60) < 10 ? "0" + parseInt(n / 60) : parseInt(n / 60), n = n % 60 < 10 ? "0" + n % 60 : n % 60), t = s + ":" + i + ":" + n) : r >= 60 && r < 3600 ? (i = parseInt(r / 60) < 10 ? "0" + parseInt(r / 60) : parseInt(r / 60), n = r % 60 < 10 ? "0" + r % 60 : r % 60, t = "00:" + i + ":" + n) : r < 60 && (n = r < 10 ? "0" + r : r, t = "00:00:" + n), t
        }

        function Ge(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Ue(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ge(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ge(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Ve = {
                name: "MessageHeader",
                props: {message: {type: Object, required: !0}},
                computed: Ue(Ue({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }, currentMemberList: function (e) {
                        return e.group.currentMemberList
                    }
                })), {}, {
                    date: function () {
                        return ke(new Date(1e3 * this.message.time))
                    }, from: function () {
                        var e = this.currentConversation.type === this.TIM.TYPES.CONV_C2C;
                        return this.isMine ? this.currentUserProfile.nick || this.currentUserProfile.userID : e ? this.currentConversation.userProfile.nick || this.currentConversation.userProfile.userID : this.currentConversation.type === this.TIM.TYPES.CONV_SYSTEM ? this.message.type === this.TIM.TYPES.MSG_GRP_SYS_NOTICE ? "群系统通知" : "系统通知" : this.message.nameCard || this.message.nick || this.message.from
                    }, isMine: function () {
                        return "out" === this.message.flow
                    }
                })
            }, Be = Ve, Ye = (s("7ef9"), Object(G["a"])(Be, _e, je, !1, null, "5bcf119e", null)), $e = Ye.exports,
            Fe = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "base",
                    class: [e.isMine ? "right" : "left"]
                }, [s("div", {staticClass: "date"}, [e._v(e._s(e.date))])])
            }, qe = [];

        function He(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function ze(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? He(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : He(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Qe = {
                name: "MessageFooter",
                props: {message: {type: Object, required: !0}},
                computed: ze(ze({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }, currentMemberList: function (e) {
                        return e.group.currentMemberList
                    }
                })), {}, {
                    date: function () {
                        return ke(new Date(1e3 * this.message.time))
                    }, from: function () {
                        var e = this.currentConversation.type === this.TIM.TYPES.CONV_C2C;
                        return this.isMine ? this.currentUserProfile.nick || this.currentUserProfile.userID : e ? this.currentConversation.userProfile.nick || this.currentConversation.userProfile.userID : this.message.nick || this.message.from
                    }, isMine: function () {
                        return "out" === this.message.flow
                    }
                })
            }, Je = Qe, Ke = (s("e017"), Object(G["a"])(Je, Fe, qe, !1, null, "11baf300", null)), Ze = Ke.exports,
            Xe = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("div", {
                    staticClass: "file-element-wrapper",
                    attrs: {title: "单击下载"},
                    on: {click: e.downloadFile}
                }, [s("div", {staticClass: "header"}, [s("i", {staticClass: "el-icon-document file-icon"}), s("div", {staticClass: "file-element"}, [s("span", {staticClass: "file-name"}, [e._v(e._s(e.fileName))]), s("span", {staticClass: "file-size"}, [e._v(e._s(e.size))])])]), e.showProgressBar ? s("el-progress", {
                    attrs: {
                        percentage: e.percentage,
                        color: function (e) {
                            return 100 === e ? "#67c23a" : "#409eff"
                        }
                    }
                }) : e._e()], 1)])
            }, We = [], et = (s("6b30"), s("c284")), tt = s.n(et), st = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "chat-bubble", on: {
                        mousedown: function (e) {
                            e.stopPropagation()
                        }, contextmenu: function (e) {
                            e.preventDefault()
                        }
                    }
                }, [e.message.isRevoked ? e._e() : s("el-dropdown", {
                    ref: "dropdown",
                    attrs: {trigger: "", placement: "bottom-start"},
                    on: {command: e.handleCommand}
                }, [s("div", {staticStyle: {display: "flex"}}, [e.isMine && e.messageReadByPeer ? s("div", {staticClass: "message-status"}, [s("span", [e._v(e._s(e.messageReadByPeer))])]) : e._e(), s("div", {
                    staticClass: "message-content",
                    class: e.bubbleStyle
                }, [e._t("default")], 2)]), s("el-dropdown-menu", {
                    attrs: {slot: "dropdown"},
                    slot: "dropdown"
                }, [e.isMine && !e.isTimeout ? s("el-dropdown-item", {attrs: {command: "revoke"}}, [e._v("撤回")]) : e._e(), s("el-dropdown-item", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: "fail" !== e.message.status,
                        expression: "message.status !=='fail'"
                    }], attrs: {command: "relay"}
                }, [e._v("转发")]), s("el-dropdown-item", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: "fail" !== e.message.status,
                        expression: "message.status !=='fail'"
                    }], attrs: {command: "merger"}
                }, [e._v("多选")])], 1)], 1), e.message.isRevoked ? s("div", {staticClass: "group-tip-element-wrapper"}, [e._v("\n    " + e._s(e.text) + "\n    "), s("el-button", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.isEdit,
                        expression: "isEdit"
                    }], staticClass: "edit-button", attrs: {type: "text", size: "mini"}, on: {click: e.reEdit}
                }, [e._v(" 重新编辑")])], 1) : e._e()], 1)
            }, it = [], nt = {
                name: "MessageBubble",
                components: {},
                data: function () {
                    return {
                        isTimeout: !1,
                        showConversationList: !1,
                        relayMessage: {},
                        selectedConversation: [],
                        testMergerMessage: {}
                    }
                },
                props: {isMine: {type: Boolean}, isNew: {type: Boolean}, message: {type: Object, required: !0}},
                created: function () {
                    this.isTimeoutHandler()
                },
                mounted: function () {
                    this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.addEventListener("mousedown", this.handleDropDownMousedown)
                },
                beforeDestroy: function () {
                    this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.removeEventListener("mousedown", this.handleDropDownMousedown)
                },
                updated: function () {
                },
                computed: {
                    bubbleStyle: function () {
                        var e = "";
                        return this.isMine ? e += "message-send" : e += "message-received", this.isNew && (e += "new"), e
                    }, text: function () {
                        return this.message.conversationType !== this.TIM.TYPES.CONV_C2C || this.isMine ? this.message.conversationType !== this.TIM.TYPES.CONV_GROUP || this.isMine ? "你撤回了一条消息" : "".concat(this.message.from, "撤回了一条消息") : "对方撤回了一条消息"
                    }, messageReadByPeer: function () {
                        return "success" === this.message.status && (this.message.conversationType === this.TIM.TYPES.CONV_C2C && this.message.isPeerRead ? "已读" : this.message.conversationType !== this.TIM.TYPES.CONV_C2C || this.message.isPeerRead ? "" : "未读")
                    }, isEdit: function () {
                        return !!this.isMine && (this.message.type === this.TIM.TYPES.MSG_TEXT && !this.isTimeout)
                    }
                },
                methods: {
                    handleDropDownMousedown: function (e) {
                        2 === e.buttons && (this.$refs.dropdown.visible ? this.$refs.dropdown.hide() : this.$refs.dropdown.show())
                    }, handleCommand: function (e) {
                        var t = this;
                        switch (e) {
                            case"revoke":
                                this.tim.revokeMessage(this.message).then((function () {
                                    t.isTimeoutHandler()
                                })).catch((function (e) {
                                    t.$store.commit("showMessage", {message: e, type: "warning"})
                                }));
                                break;
                            case"relay":
                                this.showConversationList = !0, this.$store.commit("setRelayType", 1), this.$store.commit("showConversationList", !0), this.$store.commit("setRelayMessage", this.message);
                                break;
                            case"merger":
                                this.$bus.$emit("mergerSelected", !0);
                                break;
                            default:
                                break
                        }
                    }, isTimeoutHandler: function () {
                        var e = new Date;
                        parseInt(e.getTime() / 1e3) - this.message.time > 120 ? this.isTimeout = !0 : setTimeout(this.isTimeoutHandler, 1e3)
                    }, reEdit: function () {
                        this.$bus.$emit("reEditMessage", this.message.payload.text)
                    }
                }
            }, rt = nt, ot = (s("b363"), Object(G["a"])(rt, st, it, !1, null, "0ad1fde9", null)), at = ot.exports, ct = {
                name: "FileElement",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at, ElProgress: tt.a},
                computed: {
                    fileName: function () {
                        return this.payload.fileName
                    }, fileUrl: function () {
                        return this.payload.fileUrl
                    }, size: function () {
                        var e = this.payload.fileSize;
                        return e > 1024 ? e / 1024 > 1024 ? "".concat(this.toFixed(e / 1024 / 1024), " Mb") : "".concat(this.toFixed(e / 1024), " Kb") : "".concat(this.toFixed(e), "B")
                    }, showProgressBar: function () {
                        return "unSend" === this.$parent.message.status
                    }, percentage: function () {
                        return Math.floor(100 * (this.$parent.message.progress || 0))
                    }
                },
                methods: {
                    toFixed: function (e) {
                        var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 2;
                        return e.toFixed(t)
                    }, downloadFile: function () {
                        var e = this;
                        if (window.fetch) fetch(this.fileUrl).then((function (e) {
                            return e.blob()
                        })).then((function (t) {
                            var s = document.createElement("a"), i = window.URL.createObjectURL(t);
                            s.href = i, s.download = e.fileName, s.click()
                        })); else {
                            var t = document.createElement("a");
                            t.href = this.fileUrl, t.target = "_blank", t.download = this.filename, t.click()
                        }
                    }
                }
            }, lt = ct, ut = (s("4d06"), Object(G["a"])(lt, Xe, We, !1, null, "96091f5a", null)), mt = ut.exports,
            pt = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("div", {staticClass: "face-element-wrapper"}, [s("img", {attrs: {src: e.url}})])])
            }, ht = [], ft = {
                name: "FaceElement",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at},
                computed: {
                    url: function () {
                        var e = "";
                        return e = this.payload.data.indexOf("@2x") > 0 ? this.payload.data : this.payload.data + "@2x", "https://webim-1252463788.file.myqcloud.com/assets/face-elem/".concat(e, ".png")
                    }
                }
            }, dt = ft, gt = (s("24c8"), Object(G["a"])(dt, pt, ht, !1, null, "848acce2", null)), vt = gt.exports,
            bt = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("img", {
                    staticClass: "image-element",
                    attrs: {src: e.imageUrl},
                    on: {load: e.onImageLoaded, click: e.handlePreview}
                }), e.showProgressBar ? s("el-progress", {
                    attrs: {
                        percentage: e.percentage, color: function (e) {
                            return 100 === e ? "#67c23a" : "#409eff"
                        }
                    }
                }) : e._e()], 1)
            }, Ct = [];

        function yt(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Ot(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? yt(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : yt(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var wt = {
                name: "ImageElemnt",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at, ElProgress: tt.a},
                computed: Ot(Ot({}, Object(H["b"])(["imgUrlList"])), {}, {
                    imageUrl: function () {
                        var e = this.payload.imageInfoArray[0].url;
                        return "string" !== typeof e ? "" : "//" === e.slice(0, 2) ? "https:".concat(e) : e
                    }, showProgressBar: function () {
                        return "unSend" === this.$parent.message.status
                    }, percentage: function () {
                        return Math.floor(100 * (this.$parent.message.progress || 0))
                    }
                }),
                methods: {
                    onImageLoaded: function (e) {
                        this.$bus.$emit("image-loaded", e)
                    }, handlePreview: function () {
                        this.$bus.$emit("image-preview", {url: this.payload.imageInfoArray[0].url})
                    }
                }
            }, It = wt, At = (s("cbc6"), Object(G["a"])(It, bt, Ct, !1, null, "b1c8d5ce", null)), Mt = At.exports,
            Pt = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [e._l(e.contentList, (function (t, i) {
                    return ["text" === t.name ? s("span", {
                        key: i,
                        staticClass: "text-box"
                    }, [e._v(e._s(t.text))]) : "img" === t.name ? s("img", {
                        key: i,
                        attrs: {src: t.src, width: "20px", height: "20px"}
                    }) : e._e()]
                }))], 2)
            }, Tt = [];

        function Et(e) {
            var t = [], s = e.text, i = -1, n = -1;
            while ("" !== s) switch (i = s.indexOf("["), n = s.indexOf("]"), i) {
                case 0:
                    if (-1 === n) t.push({name: "text", text: s}), s = ""; else {
                        var r = s.slice(0, n + 1);
                        fe[r] ? (t.push({
                            name: "img",
                            src: he + fe[r]
                        }), s = s.substring(n + 1)) : (t.push({name: "text", text: "["}), s = s.slice(1))
                    }
                    break;
                case-1:
                    t.push({name: "text", text: s}), s = "";
                    break;
                default:
                    t.push({name: "text", text: s.slice(0, i)}), s = s.substring(i);
                    break
            }
            return t
        }

        var Dt = {
                name: "TextElement",
                components: {MessageBubble: at},
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                computed: {
                    contentList: function () {
                        return Et(this.payload)
                    }
                }
            }, _t = Dt, jt = (s("da66"), Object(G["a"])(_t, Pt, Tt, !1, null, "87656090", null)), Lt = jt.exports,
            St = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("div", {
                    staticClass: "sound-element-wrapper",
                    attrs: {title: "单击播放"},
                    on: {click: e.play}
                }, [s("i", {staticClass: "iconfont icon-voice"}), e._v("\n    " + e._s(e.second + '"') + "\n  ")])])
            }, kt = [], xt = {
                name: "SoundElement",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at},
                data: function () {
                    return {amr: null}
                },
                computed: {
                    url: function () {
                        return this.payload.url
                    }, size: function () {
                        return this.payload.size
                    }, second: function () {
                        return this.payload.second
                    }
                },
                methods: {
                    play: function () {
                        var e = document.createElement("audio");
                        e.addEventListener("error", this.tryPlayAMR), e.src = this.url;
                        var t = e.play();
                        t && t.catch((function () {
                        }))
                    }, tryPlayAMR: function () {
                        try {
                            var e = /MSIE|Trident|Edge/.test(window.navigator.userAgent);
                            if (e) return void this.$store.commit("showMessage", {
                                message: "您的浏览器不支持该格式的语音消息播放，请尝试更换浏览器，建议使用：谷歌浏览器",
                                type: "warning"
                            });
                            if (!window.BenzAMRRecorder) {
                                var t = document.createElement("script");
                                t.addEventListener("load", this.playAMR), t.src = "./BenzAMRRecorder.js";
                                var s = document.getElementsByTagName("script")[0];
                                return void s.parentNode.insertBefore(t, s)
                            }
                            this.playAMR()
                        } catch (i) {
                            this.$store.commit("showMessage", {
                                message: "您的浏览器不支持该格式的语音消息播放，请尝试更换浏览器，建议使用：谷歌浏览器",
                                type: "warning"
                            })
                        }
                    }, playAMR: function () {
                        var e = this;
                        !this.amr && window.BenzAMRRecorder && (this.amr = new window.BenzAMRRecorder), this.amr.isInit() ? this.amr.play() : this.amr.initWithUrl(this.url).then((function () {
                            e.amr.play()
                        }))
                    }
                }
            }, Rt = xt, Nt = (s("777c"), Object(G["a"])(Rt, St, kt, !1, null, "7ab0530b", null)), Gt = Nt.exports,
            Ut = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("video", {
                    staticClass: "video",
                    attrs: {src: e.payload.videoUrl, controls: ""},
                    on: {error: e.videoError}
                }), e.showProgressBar ? s("el-progress", {
                    attrs: {
                        percentage: e.percentage, color: function (e) {
                            return 100 === e ? "#67c23a" : "#409eff"
                        }
                    }
                }) : e._e()], 1)
            }, Vt = [], Bt = {
                name: "VideoElement",
                components: {MessageBubble: at, ElProgress: tt.a},
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                computed: {
                    showProgressBar: function () {
                        return "unSend" === this.message.status
                    }, percentage: function () {
                        return Math.floor(100 * (this.$parent.message.progress || 0))
                    }
                },
                methods: {
                    videoError: function (e) {
                        this.$store.commit("showMessage", {type: "error", message: "视频出错，错误原因：" + e.target.error.message})
                    }
                }
            }, Yt = Bt, $t = (s("e3ec"), Object(G["a"])(Yt, Ut, Vt, !1, null, "6237ab78", null)), Ft = $t.exports,
            qt = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "group-tip-element-wrapper"}, [e._v(e._s(e.text))])
            }, Ht = [];
        s("ac4d"), s("8a81"), s("5df3"), s("1c4c"), s("6b54");

        function zt(e, t) {
            var s;
            if ("undefined" === typeof Symbol || null == e[Symbol.iterator]) {
                if (Array.isArray(e) || (s = Qt(e)) || t && e && "number" === typeof e.length) {
                    s && (e = s);
                    var i = 0, n = function () {
                    };
                    return {
                        s: n, n: function () {
                            return i >= e.length ? {done: !0} : {done: !1, value: e[i++]}
                        }, e: function (e) {
                            throw e
                        }, f: n
                    }
                }
                throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")
            }
            var r, o = !0, a = !1;
            return {
                s: function () {
                    s = e[Symbol.iterator]()
                }, n: function () {
                    var e = s.next();
                    return o = e.done, e
                }, e: function (e) {
                    a = !0, r = e
                }, f: function () {
                    try {
                        o || null == s.return || s.return()
                    } finally {
                        if (a) throw r
                    }
                }
            }
        }

        function Qt(e, t) {
            if (e) {
                if ("string" === typeof e) return Jt(e, t);
                var s = Object.prototype.toString.call(e).slice(8, -1);
                return "Object" === s && e.constructor && (s = e.constructor.name), "Map" === s || "Set" === s ? Array.from(e) : "Arguments" === s || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(s) ? Jt(e, t) : void 0
            }
        }

        function Jt(e, t) {
            (null == t || t > e.length) && (t = e.length);
            for (var s = 0, i = new Array(t); s < t; s++) i[s] = e[s];
            return i
        }

        function Kt(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Zt(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Kt(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Kt(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Xt = {
                name: "GroupTipElement",
                data: function () {
                    return {callTips: 256}
                },
                props: {payload: {type: Object, required: !0}, message: {type: Object, required: !1}},
                computed: Zt(Zt({}, Object(H["c"])({
                    currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }, userID: function (e) {
                        return e.user.userID
                    }
                })), {}, {
                    text: function () {
                        return this.getGroupTipContent(this.message)
                    }
                }),
                methods: {
                    getGroupTipContent: function (e) {
                        var t = e.nick || e.from === this.userID && this.currentUserProfile.nick || e.from,
                            s = e.nick || e.payload.userIDList.join(",");
                        switch (e.payload.operationType) {
                            case this.TIM.TYPES.GRP_TIP_MBR_JOIN:
                                return "群成员：".concat(s, " 加入群组");
                            case this.TIM.TYPES.GRP_TIP_MBR_QUIT:
                                return "群成员：".concat(s, " 退出群组");
                            case this.TIM.TYPES.GRP_TIP_MBR_KICKED_OUT:
                                return "群成员：".concat(s, " 被").concat(e.payload.operatorID, "踢出群组");
                            case this.TIM.TYPES.GRP_TIP_MBR_SET_ADMIN:
                                return "群成员：".concat(s, " 成为管理员");
                            case this.TIM.TYPES.GRP_TIP_MBR_CANCELED_ADMIN:
                                return "群成员：".concat(s, " 被撤销管理员");
                            case this.TIM.TYPES.GRP_TIP_GRP_PROFILE_UPDATED:
                                return "群资料修改";
                            case this.callTips:
                                return e.payload.text.indexOf("结束群聊") > -1 ? '"'.concat(e.payload.text, '"') : '"'.concat(t, '" ').concat(e.payload.text);
                            case this.TIM.TYPES.GRP_TIP_MBR_PROFILE_UPDATED:
                                var i, n = zt(e.payload.memberList);
                                try {
                                    for (n.s(); !(i = n.n()).done;) {
                                        var r = i.value;
                                        return r.muteTime > 0 ? "群成员：".concat(r.userID, "被禁言").concat(r.muteTime, "秒") : "群成员：".concat(r.userID, "被取消禁言")
                                    }
                                } catch (o) {
                                    n.e(o)
                                } finally {
                                    n.f()
                                }
                                break;
                            default:
                                return "[群提示消息]"
                        }
                    }
                }
            }, Wt = Xt, es = (s("3763"), Object(G["a"])(Wt, qt, Ht, !1, null, "7862d93a", null)), ts = es.exports,
            ss = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: !1,
                        message: e.message
                    }
                }, [s("div", {staticClass: "group-system-element-wrapper"}, [e._v("\n    " + e._s(e.text) + "\n    "), e.isJoinGroupRequest ? s("el-button", {
                    attrs: {type: "text"},
                    on: {
                        click: function (t) {
                            e.showDialog = !0
                        }
                    }
                }, [e._v("处理")]) : e._e(), s("el-dialog", {
                    attrs: {
                        title: "处理加群申请",
                        visible: e.showDialog,
                        width: "30%"
                    }, on: {
                        "update:visible": function (t) {
                            e.showDialog = t
                        }
                    }
                }, [s("el-form", {
                    ref: "form",
                    attrs: {"label-width": "100px"},
                    model: {
                        value: e.form, callback: function (t) {
                            e.form = t
                        }, expression: "form"
                    }
                }, [s("el-form-item", {attrs: {label: "处理结果："}}, [s("el-radio-group", {
                    model: {
                        value: e.form.handleAction,
                        callback: function (t) {
                            e.$set(e.form, "handleAction", t)
                        },
                        expression: "form.handleAction"
                    }
                }, [s("el-radio", {attrs: {label: "Agree"}}, [e._v("同意")]), s("el-radio", {attrs: {label: "Reject"}}, [e._v("拒绝")])], 1)], 1), s("el-form-item", {attrs: {label: "附言："}}, [s("el-input", {
                    attrs: {
                        type: "textarea",
                        resize: "none",
                        rows: 3,
                        placeholder: "请输入附言"
                    }, model: {
                        value: e.form.handleMessage, callback: function (t) {
                            e.$set(e.form, "handleMessage", t)
                        }, expression: "form.handleMessage"
                    }
                })], 1)], 1), s("span", {
                    staticClass: "dialog-footer",
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    on: {
                        click: function (t) {
                            e.showDialog = !1
                        }
                    }
                }, [e._v("取 消")]), s("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.handleGroupApplication}
                }, [e._v("确 定")])], 1)], 1)], 1)])
            }, is = [], ns = (s("b5d8"), s("f494")), rs = s.n(ns), os = (s("fe07"), s("6ac5")), as = s.n(os),
            cs = s("e23e"), ls = s.n(cs);

        function us(e) {
            var t = e.payload.groupProfile.name || e.payload.groupProfile.groupID;
            switch (e.payload.operationType) {
                case 1:
                    return "".concat(e.payload.operatorID, " 申请加入群组：").concat(t);
                case 2:
                    return "成功加入群组：".concat(t);
                case 3:
                    return "申请加入群组：".concat(t, "被拒绝");
                case 4:
                    return "你被管理员".concat(e.payload.operatorID, "踢出群组：").concat(t);
                case 5:
                    return "群：".concat(t, " 已被").concat(e.payload.operatorID, "解散");
                case 6:
                    return "".concat(e.payload.operatorID, "创建群：").concat(t);
                case 7:
                    return "".concat(e.payload.operatorID, "邀请你加群：").concat(t);
                case 8:
                    return "你退出群组：".concat(t);
                case 9:
                    return "你被".concat(e.payload.operatorID, "设置为群：").concat(t, "的管理员");
                case 10:
                    return "你被".concat(e.payload.operatorID, "撤销群：").concat(t, "的管理员身份");
                case 255:
                    return "自定义群系统通知: " + e.payload.userDefinedField
            }
        }

        function ms(e) {
            e.forEach((function (e) {
                if (!e.callType) {
                    if (e.type === ls.a.TYPES.MSG_MERGER && "" !== e.payload.downloadKey) {
                        var t = window.tim.downloadMergerMessage(e);
                        t.then((function (t) {
                            e = t
                        })).catch((function (e) {
                            console.warn("downloadMergerMessage error:", e)
                        }))
                    }
                    if (e.type === ls.a.TYPES.MSG_CUSTOM) {
                        var s = {};
                        try {
                            s = JSON.parse(e.payload.data)
                        } catch (c) {
                            s = {}
                        }
                        if (1 === s.businessID) {
                            if (e.conversationType === ls.a.TYPES.CONV_GROUP) {
                                5 === s.actionType && (e.nick = s.inviteeList ? s.inviteeList.join(",") : e.from);
                                var i = window.trtcCalling.extractCallingInfoFromMessage(e), n = "".concat(i);
                                e.type = ls.a.TYPES.MSG_GRP_TIP;
                                var r = {operationType: 256, text: n, userIDList: []};
                                e.payload = r
                            }
                            if (e.conversationType === ls.a.TYPES.CONV_C2C) {
                                var o = window.trtcCalling.extractCallingInfoFromMessage(e), a = {text: o};
                                e.payload = a
                            }
                        }
                    }
                }
            }))
        }

        var ps = {
                name: "GroupSystemNoticeElement",
                props: {payload: {type: Object, required: !0}, message: {type: Object, required: !1}},
                components: {
                    ElDialog: b.a,
                    ElForm: ne.a,
                    ElFormItem: se.a,
                    ElRadioGroup: as.a,
                    ElRadio: rs.a,
                    MessageBubble: at
                },
                data: function () {
                    return {showDialog: !1, form: {handleAction: "Agree", handleMessage: ""}}
                },
                computed: {
                    text: function () {
                        return us(this.message)
                    }, title: function () {
                        return this.message.type === this.TIM.TYPES.MSG_GRP_SYS_NOTICE ? "群系统通知" : "系统通知"
                    }, isJoinGroupRequest: function () {
                        return 1 === this.payload.operationType
                    }
                },
                methods: {
                    handleGroupApplication: function () {
                        var e = this;
                        this.tim.handleGroupApplication({
                            handleAction: this.form.handleAction,
                            handleMessage: this.form.handleMessage,
                            message: this.message
                        }).then((function () {
                            e.showDialog = !1, e.$store.commit("removeMessage", e.message)
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message}), e.showDialog = !1
                        }))
                    }
                }
            }, hs = ps, fs = (s("4011"), Object(G["a"])(hs, ss, is, !1, null, "8a49ee1a", null)), ds = fs.exports,
            gs = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("div", {staticClass: "custom-element-wrapper"}, ["survey" === this.payload.data ? s("div", {staticClass: "survey"}, [s("div", {staticClass: "title"}, [e._v("对IM DEMO的评分和建议")]), s("el-rate", {
                    attrs: {
                        disabled: "",
                        "show-score": "",
                        "text-color": "#ff9900",
                        "score-template": "{value}"
                    }, model: {
                        value: e.rate, callback: function (t) {
                            e.rate = t
                        }, expression: "rate"
                    }
                }), s("div", {staticClass: "suggestion"}, [e._v(e._s(this.payload.extension))])], 1) : s("span", {
                    staticClass: "text",
                    attrs: {title: "您可以自行解析自定义消息"}
                }, [e.text.isFromGroupLive && 1 === e.text.isFromGroupLive ? [s("message-group-live-status", {attrs: {liveInfo: e.text}})] : [e._v(e._s(e.text))]], 2)])])
            }, vs = [], bs = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "group-live-custom-message-card",
                    on: {click: e.handleClick}
                }, [s("p", {staticClass: "card-title"}, [e._v(e._s(e.cardTitle))]), s("p", {staticClass: "card-content"}, [e._v(e._s(e.cardContent))]), s("div", {staticClass: "card-footer"}, [s("img", {
                    staticClass: "avatar",
                    attrs: {src: e.roomCover, alt: ""}
                }), s("span", [e._v("群直播")])])])
            }, Cs = [], ys = (s("96cf"), s("3b8d")), Os = s("bc3a"), ws = s.n(Os);

        function Is(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function As(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Is(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Is(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Ms = {
            name: "MessageGroupLiveStatus",
            props: {liveInfo: {type: Object, required: !0}},
            computed: As(As({}, Object(H["c"])({
                userID: function (e) {
                    return e.user.userID
                }
            })), {}, {
                cardTitle: function () {
                    return "".concat(this.liveInfo.anchorName || this.liveInfo.anchorId, "的直播")
                }, cardContent: function () {
                    return 1 === Number(this.liveInfo.roomStatus) ? "正在直播" : "结束直播"
                }, roomCover: function () {
                    return this.liveInfo.roomCover || "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png"
                }
            }),
            methods: {
                handleClick: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                        var t, s, i, n, r;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    return e.next = 2, this.checkRoomExist();
                                case 2:
                                    if (t = e.sent, s = this.liveInfo, i = s.roomId, n = s.anchorId, r = s.roomName, t) {
                                        e.next = 7;
                                        break
                                    }
                                    return this.$store.commit("showMessage", {
                                        message: "直播已结束",
                                        type: "info"
                                    }), e.abrupt("return");
                                case 7:
                                    if (n !== this.userID) {
                                        e.next = 10;
                                        break
                                    }
                                    return this.$store.commit("showMessage", {
                                        message: "您正在其它终端或者Web实例上开播，请勿重复开播！",
                                        type: "info"
                                    }), e.abrupt("return");
                                case 10:
                                    this.$store.commit("updateGroupLiveInfo", {
                                        groupID: this.toAccount,
                                        roomID: i,
                                        anchorID: n,
                                        roomName: r
                                    }), this.$bus.$emit("open-group-live", {channel: 3});
                                case 12:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t() {
                        return e.apply(this, arguments)
                    }

                    return t
                }(), checkRoomExist: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                        var t, s, i;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    return e.next = 2, ws()("https://service-c2zjvuxa-1252463788.gz.apigw.tencentcs.com/release/forTest?method=getRoomList&appId=1400187352&type=groupLive");
                                case 2:
                                    return t = e.sent, s = t.data && t.data.data || [], i = [], s.forEach((function (e) {
                                        i.push(e.roomId)
                                    })), e.abrupt("return", i.includes(this.liveInfo.roomId));
                                case 7:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t() {
                        return e.apply(this, arguments)
                    }

                    return t
                }()
            }
        }, Ps = Ms, Ts = (s("7b7d"), Object(G["a"])(Ps, bs, Cs, !1, null, "d7a99570", null)), Es = Ts.exports;

        function Ds(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function _s(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ds(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ds(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var js = {
                name: "CustomElement",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at, ElRate: X.a, MessageGroupLiveStatus: Es},
                computed: _s(_s({}, Object(H["c"])({
                    currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }
                })), {}, {
                    text: function () {
                        return this.translateCustomMessage(this.payload)
                    }, rate: function () {
                        return parseInt(this.payload.description)
                    }
                }),
                methods: {
                    translateCustomMessage: function (e) {
                        var t = {};
                        try {
                            t = JSON.parse(e.data)
                        } catch (s) {
                            t = {}
                        }
                        return "group_create" === e.data ? "".concat(e.extension) : t.roomId ? (t.roomId = t.roomId.toString(), t.isFromGroupLive = 1, t) : e.text ? e.text : "[自定义消息]"
                    }
                }
            }, Ls = js, Ss = (s("1f0e"), Object(G["a"])(Ls, gs, vs, !1, null, "513fb822", null)), ks = Ss.exports,
            xs = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("a", {
                    staticClass: "geo-element",
                    attrs: {href: e.href, target: "_blank", title: "点击查看详情"}
                }, [s("span", {staticClass: "el-icon-location-outline"}, [e._v(e._s(e.payload.description))]), s("img", {attrs: {src: e.url}})])])
            }, Rs = [], Ns = {
                name: "GeoElement",
                components: {MessageBubble: at},
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                data: function () {
                    return {url: ""}
                },
                computed: {
                    lon: function () {
                        return this.payload.longitude.toFixed(6)
                    }, lat: function () {
                        return this.payload.latitude.toFixed(6)
                    }, href: function () {
                        return "https://map.qq.com/?type=marker&isopeninfowin=1&markertype=1&pointx=".concat(this.lon, "&pointy=").concat(this.lat, "&name=").concat(this.payload.description)
                    }
                },
                mounted: function () {
                    this.url = "https://apis.map.qq.com/ws/staticmap/v2/?center=".concat(this.lat, ",").concat(this.lon, "&zoom=10&size=300*150&maptype=roadmap&markers=size:large|color:0xFFCCFF|label:k|").concat(this.lat, ",").concat(this.lon, "&key=UBNBZ-PTP3P-TE7DB-LHRTI-Y4YLE-VWBBD")
                }
            }, Gs = Ns, Us = (s("4c3e"), Object(G["a"])(Gs, xs, Rs, !1, null, "116a3dab", null)), Vs = Us.exports,
            Bs = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", [s("message-bubble", {
                    attrs: {
                        isMine: e.isMine,
                        message: e.message
                    }
                }, [s("div", {
                    staticClass: "merger-box", on: {
                        click: function (t) {
                            return e.mergerHandler(e.message)
                        }
                    }
                }, [s("p", {staticClass: "merger-title"}, [e._v(e._s(e.payload.title))]), e._l(e.payload.abstractList, (function (t, i) {
                    return s("p", {
                        key: i,
                        staticClass: "merger-text"
                    }, [e._v("\n                " + e._s(t) + "\n            ")])
                }))], 2), s("span", {staticClass: "merger-text"}, [e._v(" 聊天记录")])])], 1)
            }, Ys = [], $s = {
                name: "MergerElemnt",
                props: {
                    payload: {type: Object, required: !0},
                    message: {type: Object, required: !0},
                    isMine: {type: Boolean}
                },
                components: {MessageBubble: at},
                data: function () {
                    return {mergerContment: {title: ""}}
                },
                computed: {},
                methods: {
                    mergerHandler: function (e) {
                        this.$bus.$emit("mergerMessageShow", e)
                    }, onImageLoaded: function (e) {
                        this.$bus.$emit("image-loaded", e)
                    }, handlePreview: function () {
                        this.$bus.$emit("image-preview", {url: this.payload.imageInfoArray[0].url})
                    }
                }
            }, Fs = $s, qs = (s("199a"), Object(G["a"])(Fs, Bs, Ys, !1, null, "325826da", null)), Hs = qs.exports;

        function zs(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Qs(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? zs(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : zs(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Js = {
                name: "MessageItem",
                props: {message: {type: Object, required: !0}},
                components: {
                    MessageHeader: $e,
                    MessageFooter: Ze,
                    MessageStatusIcon: De,
                    FileElement: mt,
                    FaceElement: vt,
                    ImageElement: Mt,
                    TextElement: Lt,
                    SoundElement: Gt,
                    GroupTipElement: ts,
                    GroupSystemNoticeElement: ds,
                    CustomElement: ks,
                    VideoElement: Ft,
                    GeoElement: Vs,
                    MergerElement: Hs
                },
                data: function () {
                    return {renderDom: []}
                },
                computed: Qs(Qs({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }
                })), {}, {
                    showAvatar: function () {
                        return "C2C" === this.currentConversation.type && !this.message.isRevoked || "GROUP" === this.currentConversation.type && !this.message.isRevoked && this.message.type !== this.TIM.TYPES.MSG_GRP_TIP
                    }, avatar: function () {
                        return "C2C" === this.currentConversation.type ? this.message.avatar : "GROUP" === this.currentConversation.type ? this.isMine ? this.currentUserProfile.avatar : this.message.avatar : ""
                    }, currentConversationType: function () {
                        return this.currentConversation.type
                    }, isMine: function () {
                        return "out" === this.message.flow
                    }, messagePosition: function () {
                        return ["TIMGroupTipElem", "TIMGroupSystemNoticeElem"].includes(this.message.type) || this.message.isRevoked ? "position-center" : this.isMine ? "position-right" : "position-left"
                    }, showMessageHeader: function () {
                        return !["TIMGroupTipElem", "TIMGroupSystemNoticeElem"].includes(this.message.type) && !this.message.isRevoked
                    }
                }),
                methods: {
                    showGroupMemberProfile: function (e) {
                        var t = this;
                        this.tim.getGroupMemberProfile({
                            groupID: this.message.to,
                            userIDList: [this.message.from]
                        }).then((function (s) {
                            var i = s.data.memberList;
                            i[0] && t.$bus.$emit("showMemberProfile", {event: e, member: i[0]})
                        }))
                    }
                }
            }, Ks = Js, Zs = (s("6e7b"), Object(G["a"])(Ks, we, Ie, !1, null, "200b7b86", null)), Xs = Zs.exports,
            Ws = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "conversation-profile-wrapper"}, [e.currentConversation.type === e.TIM.TYPES.CONV_C2C ? s("user-profile", {attrs: {userProfile: e.currentConversation.userProfile}}) : e.currentConversation.type === e.TIM.TYPES.CONV_GROUP ? s("group-profile", {attrs: {groupProfile: e.currentConversation.groupProfile}}) : e._e()], 1)
            }, ei = [], ti = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", [s("group-member-list", {attrs: {groupProfile: e.groupProfile}}), s("div", {staticClass: "group-info-content"}, [s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("群ID")]), s("div", {staticClass: "content"}, [e._v(e._s(e.groupProfile.groupID))])]), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        群头像\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditFaceUrl = !0, e.inputFocus("editFaceUrl")
                        }
                    }
                }) : e._e()]), e.showEditFaceUrl ? s("el-input", {
                    ref: "editFaceUrl",
                    attrs: {autofocus: "", size: "mini"},
                    on: {
                        blur: function (t) {
                            e.showEditFaceUrl = !1
                        }
                    },
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.editFaceUrl(t)
                        }
                    },
                    model: {
                        value: e.avatar, callback: function (t) {
                            e.avatar = t
                        }, expression: "avatar"
                    }
                }) : s("div", {staticClass: "content"}, [s("avatar", {attrs: {src: e.groupProfile.avatar}})], 1)], 1), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("群类型")]), s("div", {staticClass: "content"}, [e._v(e._s(e.groupType))])]), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        群名称\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditName = !0, e.inputFocus("editName")
                        }
                    }
                }) : e._e()]), e.showEditName ? s("el-input", {
                    ref: "editName",
                    attrs: {autofocus: "", size: "mini"},
                    on: {
                        blur: function (t) {
                            e.showEditName = !1
                        }
                    },
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.editName(t)
                        }
                    },
                    model: {
                        value: e.name, callback: function (t) {
                            e.name = t
                        }, expression: "name"
                    }
                }) : s("div", {
                    staticClass: "content text-ellipsis",
                    attrs: {title: e.groupProfile.name}
                }, [e._v("\n        " + e._s(e.groupProfile.name || "暂无") + "\n      ")])], 1), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        群介绍\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditIntroduction = !0, e.inputFocus("editIntroduction")
                        }
                    }
                }) : e._e()]), e.showEditIntroduction ? s("el-input", {
                    ref: "editIntroduction",
                    attrs: {autofocus: "", size: "mini"},
                    on: {
                        blur: function (t) {
                            e.showEditIntroduction = !1
                        }
                    },
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.editIntroduction(t)
                        }
                    },
                    model: {
                        value: e.introduction, callback: function (t) {
                            e.introduction = t
                        }, expression: "introduction"
                    }
                }) : s("div", {
                    staticClass: "long-content",
                    attrs: {title: e.groupProfile.introduction}
                }, [e._v("\n        " + e._s(e.groupProfile.introduction || "暂无") + "\n      ")])], 1), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        群公告\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditNotification = !0, e.inputFocus("editNotification")
                        }
                    }
                }) : e._e()]), e.showEditNotification ? s("el-input", {
                    ref: "editNotification",
                    attrs: {autofocus: "", size: "mini"},
                    on: {
                        blur: function (t) {
                            e.showEditNotification = !1
                        }
                    },
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.editNotification(t)
                        }
                    },
                    model: {
                        value: e.notification, callback: function (t) {
                            e.notification = t
                        }, expression: "notification"
                    }
                }) : s("div", {
                    staticClass: "long-content",
                    attrs: {title: e.groupProfile.notification}
                }, [e._v("\n        " + e._s(e.groupProfile.notification || "暂无") + "\n      ")])], 1), "Private" !== e.groupProfile.type ? s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        申请加群方式\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditJoinOption = !0, e.inputFocus("editJoinOption")
                        }
                    }
                }) : e._e()]), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: !e.showEditJoinOption,
                        expression: "!showEditJoinOption"
                    }], staticClass: "content"
                }, [e._v("\n        " + e._s(e.joinOptionMap[e.groupProfile.joinOption]) + "\n      ")]), s("el-select", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showEditJoinOption,
                        expression: "showEditJoinOption"
                    }], ref: "editJoinOption", attrs: {size: "mini", "automatic-dropdown": ""}, on: {
                        blur: function (t) {
                            e.showEditJoinOption = !1
                        }, change: e.editJoinOption
                    }, model: {
                        value: e.joinOption, callback: function (t) {
                            e.joinOption = t
                        }, expression: "joinOption"
                    }
                }, [s("el-option", {attrs: {label: "自由加入", value: "FreeAccess"}}), s("el-option", {
                    attrs: {
                        label: "需要验证",
                        value: "NeedPermission"
                    }
                }), s("el-option", {
                    attrs: {
                        label: "禁止加群",
                        value: "DisableApply"
                    }
                })], 1)], 1) : e._e(), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        群消息提示类型\n        "), e.editable ? s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditMessageRemindType = !0, e.inputFocus("editMessageRemindType")
                        }
                    }
                }) : e._e()]), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: !e.showEditMessageRemindType,
                        expression: "!showEditMessageRemindType"
                    }], staticClass: "content"
                }, [e._v("\n        " + e._s(e.messageRemindTypeMap[this.groupProfile.selfInfo.messageRemindType]) + "\n      ")]), s("el-select", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showEditMessageRemindType,
                        expression: "showEditMessageRemindType"
                    }],
                    ref: "editMessageRemindType",
                    attrs: {size: "mini", "automatic-dropdown": ""},
                    on: {
                        change: e.editMessageRemindType, blur: function (t) {
                            e.showEditMessageRemindType = !1
                        }
                    },
                    model: {
                        value: e.messageRemindType, callback: function (t) {
                            e.messageRemindType = t
                        }, expression: "messageRemindType"
                    }
                }, [s("el-option", {
                    attrs: {
                        label: "接收消息并提示",
                        value: "AcceptAndNotify"
                    }
                }), s("el-option", {
                    attrs: {
                        label: "接收消息但不提示",
                        value: "AcceptNotNotify"
                    }
                }), s("el-option", {
                    attrs: {
                        label: "屏蔽消息",
                        value: "Discard"
                    }
                })], 1)], 1), s("div", {staticClass: "info-item"}, [s("div", {staticClass: "label"}, [e._v("\n        我的群名片\n        "), s("i", {
                    staticClass: "el-icon-edit",
                    staticStyle: {cursor: "pointer", "font-size": "16px"},
                    on: {
                        click: function (t) {
                            e.showEditNameCard = !0, e.inputFocus("editNameCard")
                        }
                    }
                })]), e.showEditNameCard ? s("el-input", {
                    ref: "editNameCard",
                    attrs: {autofocus: "", size: "mini"},
                    on: {
                        blur: function (t) {
                            e.showEditNameCard = !1
                        }
                    },
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.editNameCard(t)
                        }
                    },
                    model: {
                        value: e.nameCard, callback: function (t) {
                            e.nameCard = t
                        }, expression: "nameCard"
                    }
                }) : s("div", {staticClass: "content cursor-pointer"}, [e._v("\n        " + e._s(e.groupProfile.selfInfo.nameCard || "暂无") + "\n      ")])], 1), s("div", {staticClass: "info-item"}, [s("div", {
                    staticClass: "label",
                    class: {active: e.active}
                }, [e._v("全体禁言")]), s("el-switch", {
                    attrs: {"active-color": "#409eff", "inactive-color": "#dcdfe6"},
                    on: {change: e.changeMuteStatus},
                    model: {
                        value: e.muteAllMembers, callback: function (t) {
                            e.muteAllMembers = t
                        }, expression: "muteAllMembers"
                    }
                })], 1), e.isOwner ? s("div", [s("el-button", {
                    attrs: {type: "text"}, on: {
                        click: function (t) {
                            e.showChangeGroupOwner = !0
                        }
                    }
                }, [e._v("转让群组")]), e.showChangeGroupOwner ? s("el-input", {
                    attrs: {
                        placeholder: "新群主的userID",
                        size: "mini"
                    }, on: {
                        blur: function (t) {
                            e.showChangeGroupOwner = !1
                        }
                    }, nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.changeOwner(t)
                        }
                    }, model: {
                        value: e.newOwnerUserID, callback: function (t) {
                            e.newOwnerUserID = t
                        }, expression: "newOwnerUserID"
                    }
                }) : e._e()], 1) : e._e(), s("div", [s("el-button", {
                    staticStyle: {color: "red"},
                    attrs: {type: "text"},
                    on: {click: e.quitGroup}
                }, [e._v("退出群组")])], 1), e.showDissmissGroup ? s("div", [s("el-button", {
                    staticStyle: {color: "red"},
                    attrs: {type: "text"},
                    on: {click: e.dismissGroup}
                }, [e._v("解散群组")])], 1) : e._e()])], 1)
            }, si = [], ii = (s("e960"), s("b35b")), ni = s.n(ii), ri = (s("6611"), s("e772")), oi = s.n(ri),
            ai = (s("1f1a"), s("4e4b")), ci = s.n(ai), li = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "group-member-list-wrapper"}, [s("div", {staticClass: "header"}, [s("span", {staticClass: "member-count text-ellipsis"}, [e._v("群成员：" + e._s(e.currentConversation.groupProfile.memberCount))]), s("popover", {
                    model: {
                        value: e.addGroupMemberVisible,
                        callback: function (t) {
                            e.addGroupMemberVisible = t
                        },
                        expression: "addGroupMemberVisible"
                    }
                }, [s("add-group-member"), s("div", {
                    staticClass: "btn-add-member",
                    attrs: {slot: "reference", title: "添加群成员"},
                    slot: "reference"
                }, [s("span", {staticClass: "tim-icon-friend-add"})])], 1)], 1), s("div", {staticClass: "scroll-content"}, [s("div", {staticClass: "group-member-list"}, e._l(e.members, (function (t) {
                    return s("div", {key: t.userID}, [s("popover", {
                        key: t.userID,
                        attrs: {placement: "right"}
                    }, [s("group-member-info", {attrs: {member: t}}), s("div", {
                        staticClass: "group-member",
                        attrs: {slot: "reference"},
                        on: {
                            click: function (s) {
                                e.currentMemberID = t.userID
                            }
                        },
                        slot: "reference"
                    }, [s("avatar", {
                        attrs: {
                            title: e.getGroupMemberAvatarText(t.role),
                            src: t.avatar
                        }
                    }), s("div", {staticClass: "member-name text-ellipsis"}, [t.nameCard ? s("span", {attrs: {title: t.nameCard}}, [e._v(e._s(t.nameCard))]) : t.nick ? s("span", {attrs: {title: t.nick}}, [e._v(e._s(t.nick))]) : s("span", {attrs: {title: t.userID}}, [e._v(e._s(t.userID))])])], 1)], 1)], 1)
                })), 0)]), s("div", {staticClass: "more"}, [e.showLoadMore ? s("el-button", {
                    attrs: {type: "text"},
                    on: {click: e.loadMore}
                }, [e._v("查看更多")]) : e._e()], 1)])
            }, ui = [], mi = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", [s("el-input", {
                    attrs: {placeholder: "输入userID后 按回车键"}, nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.addGroupMember(t)
                        }
                    }, model: {
                        value: e.userID, callback: function (t) {
                            e.userID = t
                        }, expression: "userID"
                    }
                })], 1)
            }, pi = [];
        s("a481");

        function hi(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function fi(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? hi(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : hi(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var di = {
            components: {ElInput: w.a}, data: function () {
                return {userID: ""}
            }, computed: fi({}, Object(H["c"])({
                currentConversation: function (e) {
                    return e.conversation.currentConversation
                }
            })), methods: {
                addGroupMember: function () {
                    var e = this, t = this.currentConversation.conversationID.replace("GROUP", "");
                    this.tim.addGroupMember({groupID: t, userIDList: [this.userID]}).then((function (s) {
                        var i = s.data, n = i.successUserIDList, r = i.failureUserIDList, o = i.existedUserIDList;
                        n.length > 0 && (e.$store.commit("showMessage", {
                            message: "群成员：".concat(n.join(","), "，加群成功"),
                            type: "success"
                        }), e.tim.getGroupMemberProfile({groupID: t, userIDList: n}).then((function (t) {
                            var s = t.data.memberList;
                            e.$store.commit("updateCurrentMemberList", s)
                        }))), r.length > 0 && e.$store.commit("showMessage", {
                            message: "群成员：".concat(r.join(","), "，添加失败！"),
                            type: "error"
                        }), o.length > 0 && e.$store.commit("showMessage", {message: "群成员：".concat(o.join(","), "，已在群中")})
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }
            }
        }, gi = di, vi = Object(G["a"])(gi, mi, pi, !1, null, "5d05f520", null), bi = vi.exports, Ci = function () {
            var e = this, t = e.$createElement, s = e._self._c || t;
            return s("div", [s("div", [s("span", {staticClass: "label"}, [e._v("userID:")]), e._v("\n    " + e._s(e.member.userID) + "\n    "), e.showCancelBan ? s("el-button", {
                attrs: {type: "text"},
                on: {click: e.cancelMute}
            }, [e._v("取消禁言")]) : e._e(), s("el-popover", {
                directives: [{
                    name: "show",
                    rawName: "v-show",
                    value: e.showBan,
                    expression: "showBan"
                }], attrs: {title: "禁言"}, model: {
                    value: e.popoverVisible, callback: function (t) {
                        e.popoverVisible = t
                    }, expression: "popoverVisible"
                }
            }, [s("el-input", {
                attrs: {placeholder: "请输入禁言时间"}, nativeOn: {
                    keydown: function (t) {
                        return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.setGroupMemberMuteTime(t)
                    }
                }, model: {
                    value: e.muteTime, callback: function (t) {
                        e.muteTime = t
                    }, expression: "muteTime"
                }
            }), s("el-button", {
                staticStyle: {color: "red"},
                attrs: {slot: "reference", type: "text"},
                slot: "reference"
            }, [e._v("禁言")])], 1)], 1), s("div", [s("span", {staticClass: "label"}, [e._v("nick:")]), e._v("\n    " + e._s(e.member.nick || "暂无") + "\n  ")]), s("div", [s("span", {staticClass: "label"}, [e._v("nameCard:")]), e._v("\n    " + e._s(e.member.nameCard || "暂无") + "\n    "), s("el-popover", {
                directives: [{
                    name: "show",
                    rawName: "v-show",
                    value: e.showEditNameCard,
                    expression: "showEditNameCard"
                }], attrs: {title: "修改群名片"}, model: {
                    value: e.nameCardPopoverVisible, callback: function (t) {
                        e.nameCardPopoverVisible = t
                    }, expression: "nameCardPopoverVisible"
                }
            }, [s("el-input", {
                attrs: {placeholder: "请输入群名片"}, nativeOn: {
                    keydown: function (t) {
                        return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.setGroupMemberNameCard(t)
                    }
                }, model: {
                    value: e.nameCard, callback: function (t) {
                        e.nameCard = t
                    }, expression: "nameCard"
                }
            }), s("i", {
                staticClass: "el-icon-edit",
                staticStyle: {cursor: "pointer", "font-size": "1.6rem"},
                attrs: {slot: "reference", title: "修改群名片"},
                slot: "reference"
            })], 1)], 1), s("div", [s("span", {staticClass: "label"}, [e._v("role:")]), s("span", {
                staticClass: "content role",
                attrs: {title: e.changeRoleTitle}
            }, [e._v(e._s(e.member.role))])]), e.showMuteUntil ? s("div", [s("span", {staticClass: "label"}, [e._v("禁言至:")]), s("span", {staticClass: "content"}, [e._v(e._s(e.muteUntil))])]) : e._e(), e.canChangeRole ? s("el-button", {
                attrs: {type: "text"},
                on: {click: e.changeMemberRole}
            }, [e._v("\n    " + e._s("Admin" === e.member.role ? "取消管理员" : "设为管理员") + "\n  ")]) : e._e(), e.showKickout ? s("el-button", {
                staticStyle: {color: "red"},
                attrs: {type: "text"},
                on: {click: e.kickoutGroupMember}
            }, [e._v("踢出群组")]) : e._e()], 1)
        }, yi = [];

        function Oi(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function wi(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Oi(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Oi(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Ii = {
            components: {ElPopover: n.a}, props: ["member"], data: function () {
                return {muteTime: "", popoverVisible: !1, nameCardPopoverVisible: !1, nameCard: this.member.nameCard}
            }, computed: wi(wi({}, Object(H["c"])({
                currentConversation: function (e) {
                    return e.conversation.currentConversation
                }, currentUserProfile: function (e) {
                    return e.user.currentUserProfile
                }, current: function (e) {
                    return e.current
                }
            })), {}, {
                showKickout: function () {
                    return (this.isOwner || this.isAdmin) && !this.isMine
                }, isOwner: function () {
                    return "Owner" === this.currentConversation.groupProfile.selfInfo.role
                }, isAdmin: function () {
                    return "Admin" === this.currentConversation.groupProfile.selfInfo.role
                }, isMine: function () {
                    return this.currentUserProfile.userID === this.member.userID
                }, canChangeRole: function () {
                    return this.isOwner && ["ChatRoom", "Public"].includes(this.currentConversation.subType)
                }, changeRoleTitle: function () {
                    return this.canChangeRole ? this.isOwner && "Admin" === this.member.role ? "设为：Member" : "设为：Admin" : ""
                }, showMuteUntil: function () {
                    return 1e3 * this.member.muteUntil > this.current
                }, showCancelBan: function () {
                    return !(!this.showMuteUntil || this.currentConversation.type !== this.TIM.TYPES.CONV_GROUP || this.isMine) && (this.isOwner || this.isAdmin)
                }, showBan: function () {
                    return this.currentConversation.type === this.TIM.TYPES.CONV_GROUP && (this.isOwner || this.isAdmin)
                }, showEditNameCard: function () {
                    return this.isOwner || this.isAdmin
                }, muteUntil: function () {
                    return ke(new Date(1e3 * this.member.muteUntil))
                }
            }), methods: {
                kickoutGroupMember: function () {
                    var e = this;
                    this.tim.deleteGroupMember({
                        groupID: this.currentConversation.groupProfile.groupID,
                        reason: "我要踢你出群",
                        userIDList: [this.member.userID]
                    }).then((function () {
                        e.$store.commit("deleteGroupMemeber", e.member.userID)
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }, changeMemberRole: function () {
                    var e = this;
                    if (this.canChangeRole) {
                        var t = this.member.role;
                        this.tim.setGroupMemberRole({
                            groupID: this.currentConversation.groupProfile.groupID,
                            userID: this.member.userID,
                            role: "Admin" === t ? "Member" : "Admin"
                        }).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }
                }, setGroupMemberMuteTime: function () {
                    var e = this;
                    this.tim.setGroupMemberMuteTime({
                        groupID: this.currentConversation.groupProfile.groupID,
                        userID: this.member.userID,
                        muteTime: Number(this.muteTime)
                    }).then((function () {
                        e.muteTime = "", e.popoverVisible = !1
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }, cancelMute: function () {
                    var e = this;
                    this.tim.setGroupMemberMuteTime({
                        groupID: this.currentConversation.groupProfile.groupID,
                        userID: this.member.userID,
                        muteTime: 0
                    }).then((function () {
                        e.muteTime = ""
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }, setGroupMemberNameCard: function () {
                    var e = this;
                    0 !== this.nameCard.trim().length ? this.tim.setGroupMemberNameCard({
                        groupID: this.currentConversation.groupProfile.groupID,
                        userID: this.member.userID,
                        nameCard: this.nameCard
                    }).then((function () {
                        e.nameCardPopoverVisible = !1, e.$store.commit("showMessage", {message: "修改成功"})
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    })) : this.$store.commit("showMessage", {message: "不能设置空的群名片", type: "warning"})
                }
            }
        }, Ai = Ii, Mi = (s("8656"), Object(G["a"])(Ai, Ci, yi, !1, null, "41650a64", null)), Pi = Mi.exports;

        function Ti(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Ei(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ti(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ti(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Di = {
                data: function () {
                    return {addGroupMemberVisible: !1, currentMemberID: "", count: 30}
                },
                props: ["groupProfile"],
                components: {Popover: n.a, AddGroupMember: bi, GroupMemberInfo: Pi},
                computed: Ei(Ei({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentMemberList: function (e) {
                        return e.group.currentMemberList
                    }
                })), {}, {
                    showLoadMore: function () {
                        return this.members.length < this.groupProfile.memberCount
                    }, members: function () {
                        return this.currentMemberList.slice(0, this.count)
                    }
                }),
                methods: {
                    getGroupMemberAvatarText: function (e) {
                        switch (e) {
                            case"Owner":
                                return "群主";
                            case"Admin":
                                return "管理员";
                            default:
                                return "群成员"
                        }
                    }, loadMore: function () {
                        var e = this;
                        this.$store.dispatch("getGroupMemberList", this.groupProfile.groupID).then((function () {
                            e.count += 30
                        }))
                    }
                }
            }, _i = Di, ji = (s("d1fa"), Object(G["a"])(_i, li, ui, !1, null, "7eb747b2", null)), Li = ji.exports, Si = {
                props: ["groupProfile"],
                components: {GroupMemberList: Li, ElSelect: ci.a, ElOption: oi.a, ElSwitch: ni.a},
                data: function () {
                    return {
                        showEditName: !1,
                        showEditFaceUrl: !1,
                        showEditIntroduction: !1,
                        showEditNotification: !1,
                        showEditJoinOption: !1,
                        showChangeGroupOwner: !1,
                        showEditMessageRemindType: !1,
                        showEditNameCard: !1,
                        name: this.groupProfile.name,
                        avatar: this.groupProfile.avatar,
                        introduction: this.groupProfile.introduction,
                        notification: this.groupProfile.notification,
                        joinOption: this.groupProfile.joinOption,
                        newOwnerUserID: "",
                        messageRemindType: this.groupProfile.selfInfo.messageRemindType,
                        nameCard: this.groupProfile.selfInfo.nameCard || "",
                        muteAllMembers: this.groupProfile.muteAllMembers,
                        messageRemindTypeMap: {AcceptAndNotify: "接收消息并提示", AcceptNotNotify: "接收消息但不提示", Discard: "屏蔽消息"},
                        joinOptionMap: {FreeAccess: "自由加入", NeedPermission: "需要验证", DisableApply: "禁止加群"},
                        active: !1
                    }
                },
                computed: {
                    editable: function () {
                        return this.groupProfile.type === this.TIM.TYPES.GRP_WORK || [this.TIM.TYPES.GRP_MBR_ROLE_OWNER, this.TIM.TYPES.GRP_MBR_ROLE_ADMIN].includes(this.groupProfile.selfInfo.role)
                    }, isOwner: function () {
                        return this.groupProfile.selfInfo.role === this.TIM.TYPES.GRP_MBR_ROLE_OWNER
                    }, isAdmin: function () {
                        return this.groupProfile.selfInfo.role === this.TIM.TYPES.GRP_MBR_ROLE_ADMIN
                    }, showDissmissGroup: function () {
                        return this.isOwner && this.groupProfile.type !== this.TIM.TYPES.GRP_WORK
                    }, groupType: function () {
                        switch (this.groupProfile.type) {
                            case this.TIM.TYPES.GRP_WORK:
                                return "好友工作群（Work）";
                            case this.TIM.TYPES.GRP_PUBLIC:
                                return "陌生人社交群（Public）";
                            case this.TIM.TYPES.GRP_CHATROOM:
                                return "临时会议群（Meeting）";
                            case this.TIM.TYPES.GRP_AVCHATROOM:
                                return "直播群（AVChatRoom）";
                            default:
                                return ""
                        }
                    }
                },
                watch: {
                    groupProfile: function (e) {
                        Object.assign(this, {
                            showEditName: !1,
                            showEditFaceUrl: !1,
                            showEditIntroduction: !1,
                            showEditNotification: !1,
                            showEditJoinOption: !1,
                            showChangeGroupOwner: !1,
                            showEditNameCard: !1,
                            name: e.name,
                            avatar: e.avatar,
                            introduction: e.introduction,
                            notification: e.notification,
                            joinOption: e.joinOption,
                            messageRemindType: e.messageRemindType,
                            nameCard: e.selfInfo.nameCard || "",
                            muteAllMembers: e.muteAllMembers
                        })
                    }
                },
                methods: {
                    inputFocus: function (e) {
                        var t = this;
                        this.$nextTick((function () {
                            t.$refs[e].focus()
                        }))
                    }, editName: function () {
                        var e = this;
                        this.tim.updateGroupProfile({
                            groupID: this.groupProfile.groupID,
                            name: this.name.trim()
                        }).then((function () {
                            e.showEditName = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, editFaceUrl: function () {
                        var e = this;
                        this.tim.updateGroupProfile({
                            groupID: this.groupProfile.groupID,
                            avatar: this.avatar.trim()
                        }).then((function () {
                            e.showEditFaceUrl = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, changeMuteStatus: function () {
                        var e = this;
                        this.isOwner || this.isAdmin ? this.tim.updateGroupProfile({
                            muteAllMembers: this.muteAllMembers,
                            groupID: this.groupProfile.groupID
                        }).then((function (t) {
                            e.muteAllMembers = t.data.group.muteAllMembers, e.muteAllMembers ? (e.active = !0, e.$store.commit("showMessage", {message: "全体禁言"})) : (e.active = !1, e.$store.commit("showMessage", {message: "取消全体禁言"}))
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })) : (this.muteAllMembers = this.groupProfile.muteAllMembers, this.$store.commit("showMessage", {
                            type: "error",
                            message: "普通群成员不能对全体禁言"
                        }))
                    }, editIntroduction: function () {
                        var e = this;
                        this.tim.updateGroupProfile({
                            groupID: this.groupProfile.groupID,
                            introduction: this.introduction.trim()
                        }).then((function () {
                            e.showEditIntroduction = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, editNotification: function () {
                        var e = this;
                        this.tim.updateGroupProfile({
                            groupID: this.groupProfile.groupID,
                            notification: this.notification.trim()
                        }).then((function () {
                            e.showEditNotification = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, editJoinOption: function () {
                        var e = this;
                        this.tim.updateGroupProfile({
                            groupID: this.groupProfile.groupID,
                            joinOption: this.joinOption
                        }).then((function () {
                            e.showEditJoinOption = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, changeOwner: function () {
                        var e = this;
                        this.tim.changeGroupOwner({
                            groupID: this.groupProfile.groupID,
                            newOwnerID: this.newOwnerUserID
                        }).then((function () {
                            e.showChangeGroupOwner = !1, e.$store.commit("showMessage", {message: "转让群主成功，新群主ID：".concat(e.newOwnerUserID)}), e.newOwnerUserID = ""
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, quitGroup: function () {
                        var e = this;
                        this.tim.quitGroup(this.groupProfile.groupID).then((function (t) {
                            var s = t.data.groupID;
                            e.$store.commit("showMessage", {message: "退群成功"}), s === e.groupProfile.groupID && e.$store.commit("resetCurrentConversation")
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, dismissGroup: function () {
                        var e = this;
                        this.tim.dismissGroup(this.groupProfile.groupID).then((function (t) {
                            var s = t.data.groupID;
                            e.$store.commit("showMessage", {
                                message: "群：".concat(e.groupProfile.name || e.groupProfile.groupID, "解散成功！"),
                                type: "success"
                            }), s === e.groupProfile.groupID && e.$store.commit("resetCurrentConversation")
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, editMessageRemindType: function () {
                        var e = this;
                        this.tim.setMessageRemindType({
                            groupID: this.groupProfile.groupID,
                            messageRemindType: this.messageRemindType
                        }).then((function () {
                            e.showEditMessageRemindType = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, editNameCard: function () {
                        var e = this;
                        0 !== this.nameCard.trim().length ? this.tim.setGroupMemberNameCard({
                            groupID: this.groupProfile.groupID,
                            nameCard: this.nameCard.trim()
                        }).then((function () {
                            e.showEditNameCard = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })) : this.$store.commit("showMessage", {message: "不能设置空的群名片", type: "warning"})
                    }
                }
            }, ki = Si, xi = (s("0716"), Object(G["a"])(ki, ti, si, !1, null, null, null)), Ri = xi.exports,
            Ni = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "profile-user"}, [s("avatar", {
                    attrs: {
                        title: e.userProfile.userID,
                        src: e.userProfile.avatar
                    }
                }), s("div", {staticClass: "nick-name text-ellipsis"}, [e.userProfile.nick ? s("span", {attrs: {title: e.userProfile.nick}}, [e._v("\n      " + e._s(e.userProfile.nick) + "\n    ")]) : s("span", {
                    staticClass: "anonymous",
                    attrs: {title: "该用户未设置昵称"}
                }, [e._v("\n      [Anonymous]\n    ")])]), e.genderClass ? s("div", {staticClass: "gender"}, [s("span", {
                    staticClass: "iconfont",
                    class: e.genderClass,
                    attrs: {title: e.gender}
                })]) : e._e(), e.isInBlacklist || e.userProfile.userID === e.myUserID ? e.isInBlacklist ? s("el-button", {
                    attrs: {
                        title: "将该用户移出黑名单",
                        type: "text"
                    }, on: {click: e.removeFromBlacklist}
                }, [e._v("移出黑名单")]) : e._e() : s("el-button", {
                    staticClass: "btn-add-blacklist",
                    attrs: {title: "将该用户加入黑名单", type: "text"},
                    on: {click: e.addToBlackList}
                }, [e._v("加入黑名单")])], 1)
            }, Gi = [];

        function Ui(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Vi(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ui(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ui(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Bi = {
            props: {userProfile: {type: Object, required: !0}},
            computed: Vi(Vi({}, Object(H["c"])({
                blacklist: function (e) {
                    return e.blacklist.blacklist
                }, myUserID: function (e) {
                    return e.user.currentUserProfile.userID
                }
            })), {}, {
                isInBlacklist: function () {
                    var e = this;
                    return this.blacklist.findIndex((function (t) {
                        return t.userID === e.userProfile.userID
                    })) >= 0
                }, gender: function () {
                    switch (this.userProfile.gender) {
                        case this.TIM.TYPES.GENDER_MALE:
                            return "男";
                        case this.TIM.TYPES.GENDER_FEMALE:
                            return "女";
                        default:
                            return "未设置"
                    }
                }, genderClass: function () {
                    switch (this.userProfile.gender) {
                        case this.TIM.TYPES.GENDER_MALE:
                            return "icon-male";
                        case this.TIM.TYPES.GENDER_FEMALE:
                            return "icon-female";
                        default:
                            return ""
                    }
                }
            }),
            methods: {
                addToBlackList: function () {
                    var e = this;
                    this.tim.addToBlacklist({userIDList: [this.userProfile.userID]}).then((function () {
                        e.$store.dispatch("getBlacklist")
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {message: t.message, type: "error"})
                    }))
                }, removeFromBlacklist: function () {
                    var e = this;
                    this.tim.removeFromBlacklist({userIDList: [this.userProfile.userID]}).then((function () {
                        e.$store.commit("removeFromBlacklist", e.userProfile.userID)
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }
            }
        }, Yi = Bi, $i = (s("873f"), Object(G["a"])(Yi, Ni, Gi, !1, null, "f157254a", null)), Fi = $i.exports;

        function qi(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Hi(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? qi(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : qi(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var zi = {
                name: "ConversationProfile", components: {GroupProfile: Ri, UserProfile: Fi}, data: function () {
                    return {}
                }, computed: Hi({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }
                }))
            }, Qi = zi, Ji = (s("a65e"), Object(G["a"])(Qi, Ws, ei, !1, null, "7e59049a", null)), Ki = Ji.exports,
            Zi = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("transition", {attrs: {name: "el-fade-in"}}, [s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.visible,
                        expression: "visible"
                    }],
                    ref: "member-profile-card",
                    staticClass: "member-profile-card-wrapper",
                    style: {top: e.y + "px", left: e.x + "px"}
                }, [s("div", {staticClass: "profile"}, [s("avatar", {
                    staticClass: "avatar",
                    attrs: {src: e.member.avatar}
                }), s("div", {staticClass: "basic"}, [s("span", [e._v("ID：" + e._s(e.member.userID))]), s("span", [e._v("昵称：" + e._s(e.member.nick || "暂无"))])])], 1), s("el-divider", {staticClass: "divider"}), s("div", {staticClass: "member-profile"}, [s("div", {staticClass: "item"}, [s("span", {staticClass: "label"}, [e._v("群名片")]), e._v("\n        " + e._s(e.member.nameCard || "暂无") + "\n      ")]), s("div", {staticClass: "item"}, [s("span", {staticClass: "label"}, [e._v("入群时间")]), e._v("\n        " + e._s(e.joinTime) + "\n      ")]), e.member.muteUntil ? s("div", {staticClass: "item"}, [s("span", {staticClass: "label"}, [e._v("禁言至")]), e._v("\n        " + e._s(e.muteUntil) + "\n      ")]) : e._e()]), s("el-button", {
                    staticClass: "send-message-btn",
                    attrs: {type: "primary", size: "mini", title: "发消息", icon: "el-icon-message", circle: ""},
                    on: {click: e.handleSendMessage}
                })], 1)])
            }, Xi = [], Wi = {
                name: "MemberProfileCard", components: {ElDivider: o.a}, data: function () {
                    return {member: {}, x: 0, y: 0, visible: !1}
                }, mounted: function () {
                    this.$bus.$on("showMemberProfile", this.handleShowMemberProfile, this)
                }, computed: {
                    joinTime: function () {
                        return this.member.joinTime ? ke(new Date(1e3 * this.member.joinTime)) : ""
                    }, muteUntil: function () {
                        return this.member.muteUntil ? ke(new Date(1e3 * this.member.muteUntil)) : ""
                    }
                }, methods: {
                    handleSendMessage: function () {
                        this.$store.dispatch("checkoutConversation", "C2C".concat(this.member.userID)), this.hide()
                    }, handleShowMemberProfile: function (e) {
                        var t = e.event, s = e.member;
                        this.member = s || {}, this.x = t.x, this.y = t.y, this.show()
                    }, show: function () {
                        this.visible || (window.addEventListener("click", this.handleClick, this), this.visible = !0)
                    }, hide: function () {
                        this.visible && (window.removeEventListener("click", this.handleClick, this), this.visible = !1)
                    }, handleClick: function (e) {
                        e.target !== this.$refs["member-profile-card"] && this.hide()
                    }
                }
            }, en = Wi, tn = (s("aea5"), Object(G["a"])(en, Zi, Xi, !1, null, "18b992ef", null)), sn = tn.exports,
            nn = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "merger-conversation-wrapper"}, [s("div", {
                    staticClass: "current-conversation",
                    on: {scroll: e.onScroll}
                }, [s("div", {staticClass: "content"}, [s("div", {
                    ref: "message-list",
                    staticClass: "message-list",
                    on: {scroll: this.onScroll}
                }, e._l(e.mergerList(e.mergerMessage), (function (t, i) {
                    return s("div", {key: i}, [s("div", {staticClass: "message-item"}, [s("div", {staticClass: "avatar-box"}, [s("avatar", {
                        staticClass: "group-member-avatar",
                        attrs: {src: t.avatar}
                    })], 1), s("div", {staticClass: "container-box"}, [s("div", {staticClass: "nick-date"}, [s("div", {staticClass: "name text-ellipsis"}, [e._v(e._s(t.nick || t.from || "小晨曦"))]), s("div", {staticClass: "date"}, [e._v(e._s(e.getDate(t.time)))])]), e._l(t.messageBody, (function (e, t) {
                        return s("merger-message-item", {key: t, attrs: {message: e, payload: e.payload}})
                    }))], 2)]), s("el-divider")], 1)
                })), 0)])])])
            }, rn = [], on = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "message-wrapper col-2"}, [s("div", {staticClass: "content-wrapper"}, ["TIMTextElem" === e.message.type ? s("div", {staticClass: "message-container"}, e._l(e.contentList, (function (t, i) {
                    return s("div", {
                        key: i,
                        staticClass: "text-message"
                    }, ["text" === t.name ? s("span", {key: i}, [e._v(e._s(t.text))]) : "img" === t.name ? s("img", {
                        key: i,
                        attrs: {src: t.src, width: "20px", height: "20px"}
                    }) : e._e()])
                })), 0) : "TIMImageElem" === e.message.type ? s("div", {staticClass: "message-container"}, [s("img", {
                    staticClass: "image-element",
                    attrs: {src: e.payload.imageInfoArray[0].url},
                    on: {
                        load: e.onImageLoaded, click: function (t) {
                            return e.handlePreview()
                        }
                    }
                })]) : "TIMFileElem" === e.message.type ? s("div", {staticClass: "message-container"}, [s("div", {
                    staticClass: "file-element-wrapper",
                    attrs: {title: "单击下载"},
                    on: {click: e.downloadFile}
                }, [s("div", {staticClass: "file-box"}, [s("i", {staticClass: "el-icon-document file-icon"}), s("div", {staticClass: "file-element"}, [s("span", {staticClass: "file-name"}, [e._v(e._s(e.payload.fileName))]), s("span", {staticClass: "file-size"}, [e._v(e._s(e.fileSize))])])])])]) : "TIMFaceElem" === e.message.type ? s("div", {staticClass: "message-container"}, [s("img", {attrs: {src: e.faceUrl}})]) : e.message.type === e.TIM.TYPES.MSG_VIDEO ? s("div", {staticClass: "message-container"}, [s("video", {
                    staticClass: "merger-video",
                    attrs: {src: e.payload.videoUrl, controls: ""},
                    on: {error: e.videoError}
                })]) : e.message.type === e.TIM.TYPES.MSG_AUDIO ? s("div", {
                    staticClass: "sound-element-wrapper",
                    attrs: {title: "单击播放"},
                    on: {click: e.play}
                }, [s("i", {staticClass: "iconfont icon-voice"}), e._v("\n                " + e._s(e.payload.second + '"') + "\n            ")]) : "TIMCustomElem" === e.message.type ? s("div", {staticClass: "message-container"}, [s("div", {staticClass: "custom-element-wrapper"}, ["survey" === this.payload.data ? s("div", {staticClass: "survey"}, [s("div", {staticClass: "title"}, [e._v("对IM DEMO的评分和建议")]), s("el-rate", {
                    attrs: {
                        disabled: "",
                        "show-score": "",
                        "text-color": "#ff9900",
                        "score-template": "{value}"
                    }, model: {
                        value: e.rate, callback: function (t) {
                            e.rate = t
                        }, expression: "rate"
                    }
                }), s("div", {staticClass: "suggestion"}, [e._v(e._s(this.payload.extension))])], 1) : s("span", {
                    staticClass: "text",
                    attrs: {title: "您可以自行解析自定义消息"}
                }, [[e._v(e._s(e.translateCustomMessage(this.payload)))]], 2)])]) : e.message.type === e.TIM.TYPES.MSG_MERGER ? s("div", {
                    staticClass: "message-container",
                    on: {
                        click: function (t) {
                            return e.mergerHandler(e.message)
                        }
                    }
                }, [s("div", {staticClass: "merger-item"}, [s("p", {staticClass: "merger-title"}, [e._v(e._s(e.payload.title))]), e._l(e.payload.abstractList, (function (t, i) {
                    return s("p", {
                        key: i,
                        staticClass: "merger-text"
                    }, [e._v("\n                        " + e._s(t) + "\n                    ")])
                }))], 2)]) : e._e()])])
            }, an = [];

        function cn(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function ln(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? cn(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : cn(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var un = {
            name: "MessageItem",
            props: {message: {type: Object, required: !0}, payload: {type: Object, required: !0}},
            components: {ElRate: X.a},
            data: function () {
                return {
                    renderDom: [],
                    showConversationList: !1,
                    relayMessage: {},
                    selectedConversation: [],
                    messageSelected: []
                }
            },
            computed: ln(ln({}, Object(H["c"])({
                currentConversation: function (e) {
                    return e.conversation.currentConversation
                }, currentUserProfile: function (e) {
                    return e.user.currentUserProfile
                }, isShowConversationList: function (e) {
                    return e.conversation.isShowConversationList
                }
            })), {}, {
                rate: function () {
                    return parseInt(this.payload.description)
                }, imageUrl: function () {
                    var e = this.payload.imageInfoArray[0].imageUrl;
                    return "string" !== typeof e ? "" : "//" === e.slice(0, 2) ? "https:".concat(e) : e
                }, percentage: function () {
                    return Math.floor(100 * (this.$parent.message.progress || 0))
                }, faceUrl: function () {
                    var e = "";
                    return e = this.payload.data.indexOf("@2x") > 0 ? this.payload.data : this.payload.data + "@2x", "https://webim-1252463788.file.myqcloud.com/assets/face-elem/".concat(e, ".png")
                }, date: function () {
                    return ke(new Date(1e3 * this.message.time))
                }, fileSize: function () {
                    var e = this.payload.fileSize;
                    return e > 1024 ? e / 1024 > 1024 ? "".concat(this.toFixed(e / 1024 / 1024), " Mb") : "".concat(this.toFixed(e / 1024), " Kb") : "".concat(this.toFixed(e), "B")
                }, from: function () {
                    var e = this.currentConversation.type === this.TIM.TYPES.CONV_C2C;
                    return this.isMine ? this.currentUserProfile.nick || this.currentUserProfile.userID : e ? this.currentConversation.userProfile.nick || this.currentConversation.userProfile.userID : this.message.nameCard || this.message.nick || this.message.from
                }, avatar: function () {
                    return "C2C" === this.currentConversation.type ? this.isMine ? this.currentUserProfile.avatar : this.currentConversation.userProfile.avatar : "GROUP" === this.currentConversation.type ? this.isMine ? this.currentUserProfile.avatar : this.message.avatar : ""
                }, currentConversationType: function () {
                    return this.currentConversation.type
                }, isMine: function () {
                    return "out" === this.message.flow
                }, contentList: function () {
                    return Et(this.payload)
                }
            }),
            methods: {
                translateCustomMessage: function (e) {
                    var t = {};
                    try {
                        t = JSON.parse(e.data)
                    } catch (s) {
                        t = {}
                    }
                    return "group_create" === e.data ? "".concat(e.extension) : t.roomId ? (t.roomId = t.roomId.toString(), t.isFromGroupLive = 1, t) : e.text ? e.text : "[自定义消息]"
                }, onImageLoaded: function (e) {
                    this.$bus.$emit("image-loaded", e)
                }, handlePreview: function () {
                    this.$bus.$emit("image-preview", {url: this.payload.imageInfoArray[0].url, flag: !0})
                }, toFixed: function (e) {
                    var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 2;
                    return e.toFixed(t)
                }, showGroupMemberProfile: function (e) {
                    var t = this;
                    this.tim.getGroupMemberProfile({
                        groupID: this.message.to,
                        userIDList: [this.message.from]
                    }).then((function (s) {
                        var i = s.data.memberList;
                        i[0] && t.$bus.$emit("showMemberProfile", {event: e, member: i[0]})
                    }))
                }, messageClick: function (e) {
                    this.$store.commit("showConversationList", !1), this.showConversationList = !0, this.relayMessage = e
                }, showMergerMessage: function () {
                    this.$bus.$emit("mergerMessage", !0)
                }, cancel: function () {
                    this.showConversationList = !1
                }, getList: function (e) {
                    this.selectedConversation = e
                }, messageRelay: function () {
                    var e = this, t = "", s = "";
                    this.selectedConversation.forEach((function (i) {
                        -1 !== i.indexOf(e.TIM.TYPES.CONV_C2C) && (t = e.TIM.TYPES.CONV_C2C, s = i.substring(3, i.length)), -1 !== i.indexOf(e.TIM.TYPES.CONV_GROUP) && (t = e.TIM.TYPES.CONV_GROUP, s = i.substring(5, i.length));
                        var n = e.tim.createForwardMessage({
                            to: s,
                            conversationType: t,
                            payload: e.relayMessage,
                            priority: e.TIM.TYPES.MSG_PRIORITY_NORMAL
                        });
                        e.tim.sendMessage(n).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        })), e.showConversationList = !1
                    }))
                }, mergerHandler: function (e) {
                    this.$store.commit("setMergerMessage", e)
                }, videoError: function (e) {
                    this.$store.commit("showMessage", {type: "error", message: "视频出错，错误原因：" + e.target.error.message})
                }, play: function () {
                    var e = document.createElement("audio");
                    e.addEventListener("error", this.tryPlayAMR), e.src = this.payload.url;
                    var t = e.play();
                    t && t.catch((function () {
                    }))
                }, tryPlayAMR: function () {
                    try {
                        var e = /MSIE|Trident|Edge/.test(window.navigator.userAgent);
                        if (e) return void this.$store.commit("showMessage", {
                            message: "您的浏览器不支持该格式的语音消息播放，请尝试更换浏览器，建议使用：谷歌浏览器",
                            type: "warning"
                        });
                        if (!window.BenzAMRRecorder) {
                            var t = document.createElement("script");
                            t.addEventListener("load", this.playAMR), t.src = "./BenzAMRRecorder.js";
                            var s = document.getElementsByTagName("script")[0];
                            return void s.parentNode.insertBefore(t, s)
                        }
                        this.playAMR()
                    } catch (i) {
                        this.$store.commit("showMessage", {
                            message: "您的浏览器不支持该格式的语音消息播放，请尝试更换浏览器，建议使用：谷歌浏览器",
                            type: "warning"
                        })
                    }
                }, playAMR: function () {
                    var e = this;
                    !this.amr && window.BenzAMRRecorder && (this.amr = new window.BenzAMRRecorder), this.amr.isInit() ? this.amr.play() : this.amr.initWithUrl(this.url).then((function () {
                        e.amr.play()
                    }))
                }, downloadFile: function () {
                    var e = this;
                    if (window.fetch) fetch(this.payload.fileUrl).then((function (e) {
                        return e.blob()
                    })).then((function (t) {
                        var s = document.createElement("a"), i = window.URL.createObjectURL(t);
                        s.href = i, s.download = e.payload.fileName, s.click()
                    })); else {
                        var t = document.createElement("a");
                        t.href = this.payload.fileUrl, t.target = "_blank", t.download = this.filename, t.click()
                    }
                }
            }
        }, mn = un, pn = (s("826b"), Object(G["a"])(mn, on, an, !1, null, "7c0f7b60", null)), hn = pn.exports;

        function fn(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function dn(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? fn(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : fn(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var gn = {
                name: "CurrentConversation", components: {MergerMessageItem: hn}, data: function () {
                    return {preScrollHeight: 0, mergerMessageList: [], showMessage: null}
                }, computed: dn(dn({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, mergerMessage: function (e) {
                        return e.conversation.mergerMessage
                    }
                })), {}, {
                    mergerList: function () {
                        return function (e) {
                            return e.payload.messageList
                        }
                    }
                }), created: function () {
                }, mounted: function () {
                }, updated: function () {
                }, watch: {}, methods: {
                    getDate: function (e) {
                        return ke(new Date(1e3 * e))
                    }, onScroll: function (e) {
                        var t = e.target.scrollTop, s = this.$refs["message-list"];
                        s && this.preScrollHeight - s.clientHeight - t < 20 && (this.isShowScrollButtomTips = !1)
                    }, keepMessageListOnButtom: function () {
                        var e = this.$refs["message-list"];
                        e && (this.preScrollHeight - e.clientHeight - e.scrollTop < 20 ? (this.$nextTick((function () {
                            e.scrollTop = e.scrollHeight
                        })), this.isShowScrollButtomTips = !1) : this.isShowScrollButtomTips = !0, this.preScrollHeight = e.scrollHeight)
                    }, scrollMessageListToButtom: function () {
                        var e = this;
                        this.$nextTick((function () {
                            var t = e.$refs["message-list"];
                            t && (t.scrollTop = t.scrollHeight, e.preScrollHeight = t.scrollHeight, e.isShowScrollButtomTips = !1)
                        }))
                    }, onImageLoaded: function () {
                        this.keepMessageListOnButtom()
                    }
                }
            }, vn = gn, bn = (s("5724"), Object(G["a"])(vn, nn, rn, !1, null, "c289d350", null)), Cn = bn.exports,
            yn = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "chat-bubble", on: {
                        mousedown: function (e) {
                            e.stopPropagation()
                        }, contextmenu: function (e) {
                            e.preventDefault()
                        }
                    }
                }, [s("div", {staticClass: "conversation-container"}, [s("ConversationSelectedList", {on: {getList: e.getList}}), s("div", {staticClass: "conversation-list-btn"}, [s("span", {
                    staticClass: "conversation-btn",
                    on: {click: e.cancel}
                }, [e._v("取消")]), s("span", {
                    staticClass: "conversation-btn",
                    on: {click: e.messageRelay}
                }, [e._v("发送")])])], 1)])
            }, On = [], wn = (s("55dd"), function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "group-member-list-wrapper"}, [e._m(0), s("div", {staticClass: "scroll-content"}, [s("div", {staticClass: "group-member-list"}, [s("el-checkbox-group", {
                    on: {change: e.handleCheckedConversationChange},
                    model: {
                        value: e.conversationSelected, callback: function (t) {
                            e.conversationSelected = t
                        }, expression: "conversationSelected"
                    }
                }, e._l(e.conversationList, (function (t) {
                    return s("el-checkbox", {
                        key: t.conversationID,
                        attrs: {label: t.conversationID}
                    }, [s("div", {staticClass: "conversation-item-container"}, [s("div", {staticClass: "warp"}, [s("avatar", {
                        attrs: {
                            src: e.avatar(t),
                            type: t.type
                        }
                    }), s("div", {staticClass: "content"}, [s("div", {staticClass: "row-1"}, [s("div", {staticClass: "name"}, [s("div", {staticClass: "text-ellipsis"}, [t.type === e.TIM.TYPES.CONV_C2C ? s("span", {attrs: {title: t.userProfile.nick || t.userProfile.userID}}, [e._v(e._s(t.userProfile.nick || t.userProfile.userID) + "\n                ")]) : t.type === e.TIM.TYPES.CONV_GROUP ? s("span", {attrs: {title: t.groupProfile.name || t.groupProfile.groupID}}, [e._v(e._s(t.groupProfile.name || t.groupProfile.groupID) + "\n                ")]) : t.type === e.TIM.TYPES.CONV_SYSTEM ? s("span", [e._v("系统通知\n                ")]) : e._e()])])])])], 1)])])
                })), 1)], 1)]), s("div", {staticClass: "more"})])
            }), In = [function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "header"}, [s("span", {staticClass: "member-count text-ellipsis"}, [e._v("会话列表")])])
            }];

        function An(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Mn(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? An(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : An(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Pn = {
            props: ["type"], data: function () {
                return {conversationSelected: [], addGroupMemberVisible: !1, currentMemberID: "", count: 30}
            }, components: {}, computed: Mn(Mn({}, Object(H["c"])({
                userID: function (e) {
                    return e.user.userID
                }, currentConversation: function (e) {
                    return e.conversation.currentConversation
                }, conversationList: function (e) {
                    return e.conversation.conversationList
                }
            })), {}, {
                showLoadMore: function () {
                    return this.members.length < this.currentConversation.groupProfile.memberCount
                }, avatar: function () {
                    return function (e) {
                        switch (e.type) {
                            case"GROUP":
                                return e.groupProfile.avatar;
                            case"C2C":
                                return e.userProfile.avatar;
                            default:
                                return ""
                        }
                    }
                }, members: function () {
                    return this.currentMemberList.slice(0, this.count)
                }
            }), methods: {
                handleCheckedConversationChange: function () {
                    this.$emit("getList", this.conversationSelected)
                }, loadMore: function () {
                    var e = this;
                    this.$store.dispatch("getGroupMemberList", this.groupProfile.groupID).then((function () {
                        e.count += 30
                    }))
                }
            }
        }, Tn = Pn, En = (s("60ed"), Object(G["a"])(Tn, wn, In, !1, null, "12109d0c", null)), Dn = En.exports;

        function _n(e, t) {
            var s;
            if ("undefined" === typeof Symbol || null == e[Symbol.iterator]) {
                if (Array.isArray(e) || (s = jn(e)) || t && e && "number" === typeof e.length) {
                    s && (e = s);
                    var i = 0, n = function () {
                    };
                    return {
                        s: n, n: function () {
                            return i >= e.length ? {done: !0} : {done: !1, value: e[i++]}
                        }, e: function (e) {
                            throw e
                        }, f: n
                    }
                }
                throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")
            }
            var r, o = !0, a = !1;
            return {
                s: function () {
                    s = e[Symbol.iterator]()
                }, n: function () {
                    var e = s.next();
                    return o = e.done, e
                }, e: function (e) {
                    a = !0, r = e
                }, f: function () {
                    try {
                        o || null == s.return || s.return()
                    } finally {
                        if (a) throw r
                    }
                }
            }
        }

        function jn(e, t) {
            if (e) {
                if ("string" === typeof e) return Ln(e, t);
                var s = Object.prototype.toString.call(e).slice(8, -1);
                return "Object" === s && e.constructor && (s = e.constructor.name), "Map" === s || "Set" === s ? Array.from(e) : "Arguments" === s || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(s) ? Ln(e, t) : void 0
            }
        }

        function Ln(e, t) {
            (null == t || t > e.length) && (t = e.length);
            for (var s = 0, i = new Array(t); s < t; s++) i[s] = e[s];
            return i
        }

        function Sn(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function kn(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Sn(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Sn(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var xn = {
                name: "MessageBubble", components: {ConversationSelectedList: Dn}, data: function () {
                    return {isTimeout: !1, showConversationList: !1, selectedConversation: [], testMergerMessage: {}}
                }, created: function () {
                }, mounted: function () {
                    this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.addEventListener("mousedown", this.handleDropDownMousedown)
                }, beforeDestroy: function () {
                    this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.removeEventListener("mousedown", this.handleDropDownMousedown)
                }, updated: function () {
                }, computed: kn({}, Object(H["c"])({
                    isShowConversationList: function (e) {
                        return e.conversation.isShowConversationList
                    }, selectedMessageList: function (e) {
                        return e.conversation.selectedMessageList
                    }, relayType: function (e) {
                        return e.conversation.relayType
                    }, relayMessage: function (e) {
                        return e.conversation.relayMessage
                    }
                })), methods: {
                    cancel: function () {
                        this.$store.commit("showConversationList", !1)
                    }, getList: function (e) {
                        this.selectedConversation = e
                    }, sendSingleMessage: function (e, t, s) {
                        var i = this.tim.createForwardMessage({
                            to: e,
                            conversationType: t,
                            payload: s,
                            priority: this.TIM.TYPES.MSG_PRIORITY_NORMAL
                        });
                        return this.$store.commit("pushCurrentMessageList", i), i
                    }, mergerSort: function () {
                        this.selectedMessageList.sort((function (e, t) {
                            return e.time !== t.time ? e.time - t.time : e.sequence - t.sequence
                        }))
                    }, mergerMessage: function (e, t) {
                        var s = [], i = 0, n = "";
                        i = this.selectedMessageList.length < 3 ? this.selectedMessageList.length : 3;
                        for (var r = 0; r < i; r++) s.push(this.setAbstractList(this.selectedMessageList[r]));
                        n = "GROUP" === this.selectedMessageList[0].conversationType ? "群聊的聊天记录" : "".concat(this.selectedMessageList[0].nick || this.selectedMessageList[0].from, " 和 ").concat(this.selectedMessageList[0].to, " 的聊天记录");
                        var o = this.tim.createMergerMessage({
                            to: e,
                            conversationType: t,
                            payload: {
                                messageList: this.selectedMessageList,
                                title: n,
                                abstractList: s,
                                compatibleText: "请升级IMSDK到v2.10.1或更高版本查看此消息"
                            }
                        });
                        return this.$store.commit("pushCurrentMessageList", o), o
                    }, messageRelay: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            var t, s, i, n, r, o, a, c, l, u, m, p = this;
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        t = "", s = "", i = _n(this.selectedConversation), e.prev = 2, i.s();
                                    case 4:
                                        if ((n = i.n()).done) {
                                            e.next = 37;
                                            break
                                        }
                                        if (r = n.value, -1 !== r.indexOf(this.TIM.TYPES.CONV_C2C) && (t = this.TIM.TYPES.CONV_C2C, s = r.substring(3, r.length)), -1 !== r.indexOf(this.TIM.TYPES.CONV_GROUP) && (t = this.TIM.TYPES.CONV_GROUP, s = r.substring(5, r.length)), this.mergerSort(), 1 === this.relayType && (o = this.sendSingleMessage(s, t, this.relayMessage), this.sendMessageHandler(o)), 2 !== this.relayType) {
                                            e.next = 34;
                                            break
                                        }
                                        if (!(this.selectedMessageList.length > 30)) {
                                            e.next = 14;
                                            break
                                        }
                                        return this.$store.commit("showMessage", {
                                            message: "转发消息仅支持30条以内",
                                            type: "error"
                                        }), e.abrupt("return");
                                    case 14:
                                        a = _n(this.selectedMessageList), e.prev = 15, a.s();
                                    case 17:
                                        if ((c = a.n()).done) {
                                            e.next = 26;
                                            break
                                        }
                                        return l = c.value, e.next = 21, this.sendSingleMessage(s, t, l);
                                    case 21:
                                        return u = e.sent, e.next = 24, this.tim.sendMessage(u).then((function (e) {
                                            return e
                                        })).catch((function (e) {
                                            p.$store.commit("showMessage", {message: e.message, type: "error"})
                                        }));
                                    case 24:
                                        e.next = 17;
                                        break;
                                    case 26:
                                        e.next = 31;
                                        break;
                                    case 28:
                                        e.prev = 28, e.t0 = e["catch"](15), a.e(e.t0);
                                    case 31:
                                        return e.prev = 31, a.f(), e.finish(31);
                                    case 34:
                                        3 === this.relayType && (m = this.mergerMessage(s, t), this.sendMessageHandler(m));
                                    case 35:
                                        e.next = 4;
                                        break;
                                    case 37:
                                        e.next = 42;
                                        break;
                                    case 39:
                                        e.prev = 39, e.t1 = e["catch"](2), i.e(e.t1);
                                    case 42:
                                        return e.prev = 42, i.f(), e.finish(42);
                                    case 45:
                                        this.$store.commit("showConversationList", !1), this.$store.commit("resetSelectedMessage", !1);
                                    case 47:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this, [[2, 39, 42, 45], [15, 28, 31, 34]])
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), sendMessageHandler: function (e) {
                        var t = this;
                        this.tim.sendMessage(e).catch((function (e) {
                            t.$store.commit("showMessage", {message: e.message, type: "error"})
                        }))
                    }, setAbstractList: function (e) {
                        var t = e.nick || e.from;
                        switch (e.type) {
                            case this.TIM.TYPES.MSG_TEXT:
                                return "".concat(t, ": ").concat(e.payload.text);
                            case this.TIM.TYPES.MSG_MERGER:
                                return "".concat(t, ": [聊天记录]");
                            case this.TIM.TYPES.MSG_IMAGE:
                                return "".concat(t, ": [图片]");
                            case this.TIM.TYPES.MSG_AUDIO:
                                return "".concat(t, ": [音频]");
                            case this.TIM.TYPES.MSG_VIDEO:
                                return "".concat(t, ": [视频]");
                            case this.TIM.TYPES.MSG_CUSTOM:
                                return "".concat(t, ": [自定义消息]");
                            case this.TIM.TYPES.MSG_FILE:
                                return "".concat(t, ": [文件]");
                            case this.TIM.TYPES.MSG_FACE:
                                return "".concat(t, ": [动画表情]")
                        }
                    }, handleDropDownMousedown: function (e) {
                        2 === e.buttons && (this.$refs.dropdown.visible ? this.$refs.dropdown.hide() : this.$refs.dropdown.show())
                    }
                }
            }, Rn = xn, Nn = (s("96cb"), Object(G["a"])(Rn, yn, On, !1, null, "23d3b6d2", null)), Gn = Nn.exports,
            Un = s("0c23"), Vn = s.n(Un);

        function Bn(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Yn(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Bn(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Bn(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var $n = {
                name: "CurrentConversation",
                components: {
                    MessageSendBox: Oe,
                    MessageItem: Xs,
                    ConversationProfile: Ki,
                    MemberProfileCard: sn,
                    MessageMerger: Cn,
                    MessageRelay: Gn
                },
                data: function () {
                    return {
                        close: Vn.a,
                        isShowScrollButtomTips: !1,
                        preScrollHeight: 0,
                        showConversationProfile: !1,
                        timeout: "",
                        checkList: [],
                        selectedMessageList: [],
                        mergerMessagePop: !1,
                        mergerMessage: null,
                        positionX: 0,
                        positionY: 0
                    }
                },
                computed: Yn(Yn(Yn({}, Object(H["c"])({
                    currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, currentUnreadCount: function (e) {
                        return e.conversation.currentConversation.unreadCount
                    }, currentMessageList: function (e) {
                        return e.conversation.currentMessageList
                    }, isCompleted: function (e) {
                        return e.conversation.isCompleted
                    }, mergerMessageList: function (e) {
                        return e.conversation.mergerMessageList
                    }, isShowConversationList: function (e) {
                        return e.conversation.isShowConversationList
                    }, selectMessage: function (e) {
                        return e.conversation.selectMessage
                    }
                })), Object(H["b"])(["toAccount", "hidden"])), {}, {
                    showCurrentConversation: function () {
                        return !!this.currentConversation.conversationID
                    }, name: function () {
                        if ("C2C" === this.currentConversation.type) {
                            for (var e = this.currentConversation.userProfile.nick || this.toAccount, t = this.currentMessageList, s = t.length, i = s - 1; i >= 0; i--) if ("in" === t[i].flow && t[i].nick && e !== t[i].nick) {
                                e = t[i].nick;
                                break
                            }
                            return e
                        }
                        return "GROUP" === this.currentConversation.type ? this.currentConversation.groupProfile.name || this.toAccount : "@TIM#SYSTEM" === this.currentConversation.conversationID ? "系统通知" : this.toAccount
                    }, showMessageSendBox: function () {
                        return this.currentConversation.type !== this.TIM.TYPES.CONV_SYSTEM
                    }, mergerTitle: function () {
                        return this.mergerMessage && this.mergerMessage.payload.title || "聊天记录"
                    }
                }),
                mounted: function () {
                    if (this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.addEventListener("mousedown", this.move), this.$bus.$on("image-loaded", this.onImageLoaded), this.$bus.$on("scroll-bottom", this.scrollMessageListToButtom), this.$bus.$on("mergerSelected", this.mergerSelectedHandler), this.$bus.$on("mergerMessageShow", this.mergerShow), "@TIM#SYSTEM" === this.currentConversation.conversationID) return !1
                },
                beforeDestroy: function () {
                    this.$refs.dropdown && this.$refs.dropdown.$el && this.$refs.dropdown.$el.removeEventListener("mousedown", this.move)
                },
                updated: function () {
                    this.keepMessageListOnButtom(), "@TIM#SYSTEM" !== this.currentConversation.conversationID && "undefined" !== typeof this.currentConversation.conversationID || (this.showConversationProfile = !1)
                },
                watch: {
                    currentUnreadCount: function (e) {
                        !this.hidden && e > 0 && this.tim.setMessageRead({conversationID: this.currentConversation.conversationID})
                    }, hidden: function (e) {
                        !e && this.currentUnreadCount > 0 && this.tim.setMessageRead({conversationID: this.currentConversation.conversationID})
                    }
                },
                methods: {
                    move: function (e) {
                        var t = this, s = this.$refs.dropdown.$el.children[0], i = e.clientX - s.offsetLeft,
                            n = e.clientY - s.offsetTop;
                        document.onmousemove = function (e) {
                            var r = e.clientX - i, o = e.clientY - n;
                            t.positionX = o, t.positionY = r, s.style.left = r + "px", s.style.top = o + "px"
                        }, document.onmouseup = function () {
                            document.onmousemove = null, document.onmouseup = null
                        }
                    }, mergerMessageBack: function () {
                        var e = this.mergerMessageList.length - 2;
                        this.$store.commit("updateMergerMessage", this.mergerMessageList[e])
                    }, closeMessagePop: function () {
                        this.mergerMessagePop = !1, this.$store.commit("resetMergerMessage")
                    }, closeSelectMessage: function () {
                        this.$store.commit("resetSelectedMessage", !1)
                    }, mergerSelectedHandler: function () {
                        this.selectedMessageList = [], this.checkList = [], this.$store.commit("setSelectedMessage", !0)
                    }, mergerShow: function (e) {
                        this.mergerMessagePop = !0, this.mergerMessage = e, this.$store.commit("setMergerMessage", e)
                    }, mergerRelay: function () {
                        this.selectedMessage(), this.$store.commit("setRelayType", 3)
                    }, singleRelay: function () {
                        this.selectedMessage(), this.$store.commit("setRelayType", 2)
                    }, selectedMessage: function () {
                        var e = this, t = [];
                        this.selectedMessageList = [], this.checkList.forEach((function (s) {
                            t = e.currentMessageList.find((function (e) {
                                return e.ID === s
                            })), e.selectedMessageList.push(t)
                        })), this.$store.commit("showConversationList", !0), this.$store.commit("setSelectedMessageList", this.selectedMessageList)
                    }, onScroll: function (e) {
                        var t = e.target.scrollTop, s = this.$refs["message-list"];
                        s && this.preScrollHeight - s.clientHeight - t < 20 && (this.isShowScrollButtomTips = !1)
                    }, keepMessageListOnButtom: function () {
                        var e = this.$refs["message-list"];
                        e && (this.preScrollHeight - e.clientHeight - e.scrollTop < 20 ? (this.$nextTick((function () {
                            e.scrollTop = e.scrollHeight
                        })), this.isShowScrollButtomTips = !1) : this.isShowScrollButtomTips = !0, this.preScrollHeight = e.scrollHeight)
                    }, scrollMessageListToButtom: function () {
                        var e = this;
                        this.$nextTick((function () {
                            var t = e.$refs["message-list"];
                            t && (t.scrollTop = t.scrollHeight, e.preScrollHeight = t.scrollHeight, e.isShowScrollButtomTips = !1)
                        }))
                    }, showMore: function () {
                        this.showConversationProfile = !this.showConversationProfile
                    }, onImageLoaded: function () {
                        this.keepMessageListOnButtom()
                    }
                }
            }, Fn = $n, qn = (s("1239"), Object(G["a"])(Fn, z, Q, !1, null, "46def388", null)), Hn = qn.exports,
            zn = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "side-bar-wrapper"}, [s("div", {staticClass: "bar-left"}, [s("my-profile"), s("div", {
                    staticClass: "tab-items",
                    on: {click: e.handleClick}
                }, [s("div", {
                    staticClass: "iconfont icon-conversation",
                    class: {active: e.showConversationList},
                    attrs: {id: "conversation-list", title: "会话列表"}
                }, [0 !== e.totalUnreadCount ? s("sup", {staticClass: "unread"}, [e.totalUnreadCount > 99 ? [e._v("99+")] : [e._v(e._s(e.totalUnreadCount))]], 2) : e._e()]), s("div", {
                    staticClass: "iconfont icon-group",
                    class: {active: e.showGroupList},
                    attrs: {id: "group-list", title: "群组列表"}
                }), s("div", {
                    staticClass: "iconfont icon-contact",
                    class: {active: e.showFriendList},
                    attrs: {id: "friend-list", title: "好友列表"}
                }), s("div", {
                    staticClass: "iconfont icon-blacklist",
                    class: {active: e.showBlackList},
                    attrs: {id: "black-list", title: "黑名单列表"}
                }), s("div", {
                    staticClass: "group-live",
                    attrs: {id: "group-live", title: "群直播"}
                })]), s("div", {staticClass: "bottom"}, [s("div", {
                    staticClass: "iconfont icon-tuichu",
                    attrs: {title: "退出"},
                    on: {
                        click: function (t) {
                            return e.$store.dispatch("logout")
                        }
                    }
                })])], 1), s("div", {staticClass: "bar-right"}, [s("conversation-list", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showConversationList,
                        expression: "showConversationList"
                    }]
                }), s("group-list", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showGroupList,
                        expression: "showGroupList"
                    }]
                }), s("friend-list", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showFriendList,
                        expression: "showFriendList"
                    }]
                }), s("black-list", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showBlackList,
                        expression: "showBlackList"
                    }]
                })], 1)])
            }, Qn = [], Jn = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "my-profile-wrapper"}, [s("el-dialog", {
                    attrs: {
                        title: "编辑个人资料",
                        visible: e.showEditMyProfile,
                        width: "30%"
                    }, on: {
                        "update:visible": function (t) {
                            e.showEditMyProfile = t
                        }
                    }
                }, [s("el-form", {
                    attrs: {"label-width": "100px"}, model: {
                        value: e.form, callback: function (t) {
                            e.form = t
                        }, expression: "form"
                    }
                }, [s("el-form-item", {attrs: {label: "头像"}}, [s("el-input", {
                    attrs: {placeholder: "头像地址(URL)"},
                    model: {
                        value: e.form.avatar, callback: function (t) {
                            e.$set(e.form, "avatar", t)
                        }, expression: "form.avatar"
                    }
                })], 1), s("el-form-item", {attrs: {label: "昵称"}}, [s("el-input", {
                    attrs: {placeholder: "昵称"},
                    model: {
                        value: e.form.nick, callback: function (t) {
                            e.$set(e.form, "nick", t)
                        }, expression: "form.nick"
                    }
                })], 1), s("el-form-item", {attrs: {label: "性别"}}, [s("el-radio-group", {
                    model: {
                        value: e.form.gender,
                        callback: function (t) {
                            e.$set(e.form, "gender", t)
                        },
                        expression: "form.gender"
                    }
                }, [s("el-radio", {attrs: {label: e.TIM.TYPES.GENDER_MALE}}, [e._v("男")]), s("el-radio", {attrs: {label: e.TIM.TYPES.GENDER_FEMALE}}, [e._v("女")]), s("el-radio", {attrs: {label: e.TIM.TYPES.GENDER_UNKNOWN}}, [e._v("不显示")])], 1)], 1)], 1), s("span", {
                    staticClass: "dialog-footer",
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    on: {
                        click: function (t) {
                            e.showEditMyProfile = !1
                        }
                    }
                }, [e._v("取 消")]), s("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.editMyProfile}
                }, [e._v("确 定")])], 1)], 1), s("el-popover", {
                    staticClass: "popover",
                    attrs: {width: 200, trigger: "click", placement: "right"}
                }, [s("profile-card", {attrs: {profile: e.currentUserProfile}}), s("i", {
                    staticClass: "el-icon-setting edit-my-profile",
                    on: {click: e.handleEdit}
                }), s("avatar", {
                    staticClass: "my-avatar",
                    attrs: {slot: "reference", src: e.currentUserProfile.avatar},
                    slot: "reference"
                })], 1)], 1)
            }, Kn = [], Zn = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "profile-card-wrapper"}, [s("div", {staticClass: "content"}, [s("avatar", {attrs: {src: e.profile.avatar}}), s("div", {staticClass: "basic"}, [s("span", {staticClass: "nick text-ellipsis"}, [e._v(e._s(e.profile.nick || e.profile.userID))]), s("span", {
                    staticClass: "iconfont",
                    class: e.genderClass
                })])], 1)])
            }, Xn = [], Wn = {
                name: "ProfileCard",
                props: {profile: {type: Object, required: !0}},
                computed: {
                    genderClass: function () {
                        switch (this.profile.gender) {
                            case this.TIM.TYPES.GENDER_MALE:
                                return "icon-male";
                            case this.TIM.TYPES.GENDER_FEMALE:
                                return "icon-female";
                            default:
                                return "暂无"
                        }
                    }
                }
            }, er = Wn, tr = (s("8127"), Object(G["a"])(er, Zn, Xn, !1, null, null, null)), sr = tr.exports;

        function ir(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function nr(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? ir(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : ir(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var rr = {
                name: "MyProfile",
                components: {
                    ElPopover: n.a,
                    ProfileCard: sr,
                    ElForm: ne.a,
                    ElFormItem: se.a,
                    ElRadioGroup: as.a,
                    ElRadio: rs.a
                },
                data: function () {
                    return {showEditMyProfile: !1, form: {avatar: "", nick: "", gender: ""}}
                },
                computed: nr(nr({}, Object(H["c"])({
                    currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }, currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }
                })), {}, {
                    gender: function () {
                        switch (this.currentUserProfile.gender) {
                            case this.TIM.TYPES.GENDER_MALE:
                                return "男";
                            case this.TIM.TYPES.GENDER_FEMALE:
                                return "女";
                            default:
                                return "暂无"
                        }
                    }
                }),
                methods: {
                    editMyProfile: function () {
                        var e = this;
                        if (this.form.avatar && -1 === this.form.avatar.indexOf("http")) return this.$store.commit("showMessage", {
                            message: "头像应该是 Url 地址",
                            type: "warning"
                        }), void (this.form.avatar = "");
                        var t = {};
                        Object.keys(this.form).forEach((function (s) {
                            e.form[s] && (t[s] = e.form[s])
                        })), this.tim.updateMyProfile(t).then((function () {
                            e.$store.commit("showMessage", {message: "修改成功"}), e.showEditMyProfile = !1
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {message: t.message, type: "error"})
                        }))
                    }, handleEdit: function () {
                        var e = this.currentUserProfile, t = e.avatar, s = e.nick, i = e.gender;
                        Object.assign(this.form, {avatar: t, nick: s, gender: i}), this.showEditMyProfile = !0
                    }
                }
            }, or = rr, ar = (s("9462"), Object(G["a"])(or, Jn, Kn, !1, null, "1e0cccf6", null)), cr = ar.exports,
            lr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "list-container"}, [s("div", {staticClass: "header-bar"}, [s("button", {
                    attrs: {title: "刷新列表"},
                    on: {click: e.handleRefresh}
                }, [s("i", {staticClass: "tim-icon-refresh"})]), s("button", {
                    attrs: {title: "创建会话"},
                    on: {click: e.handleAddButtonClick}
                }, [s("i", {staticClass: "tim-icon-add"})])]), s("div", {staticClass: "scroll-container"}, e._l(e.conversationList, (function (e) {
                    return s("conversation-item", {key: e.conversationID, attrs: {conversation: e}})
                })), 1), s("el-dialog", {
                    attrs: {title: "快速发起会话", visible: e.showDialog, width: "30%"},
                    on: {
                        "update:visible": function (t) {
                            e.showDialog = t
                        }
                    }
                }, [s("el-input", {
                    attrs: {placeholder: "请输入用户ID"}, nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.handleConfirm(t)
                        }
                    }, model: {
                        value: e.userID, callback: function (t) {
                            e.userID = t
                        }, expression: "userID"
                    }
                }), s("span", {
                    staticClass: "dialog-footer",
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    on: {
                        click: function (t) {
                            e.showDialog = !1
                        }
                    }
                }, [e._v("取 消")]), s("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.handleConfirm}
                }, [e._v("确 定")])], 1)], 1)], 1)
            }, ur = [], mr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "conversation-item-container",
                    class: {choose: e.conversation.conversationID === e.currentConversation.conversationID},
                    on: {click: e.selectConversation}
                }, [s("div", {staticClass: "close-btn"}, [s("span", {
                    staticClass: "tim-icon-close",
                    attrs: {title: "删除会话"},
                    on: {click: e.deleteConversation}
                })]), s("div", {staticClass: "warp"}, [s("avatar", {
                    attrs: {
                        src: e.avatar,
                        type: e.conversation.type
                    }
                }), s("div", {staticClass: "content"}, [s("div", {staticClass: "row-1"}, [s("div", {staticClass: "name"}, [s("div", {staticClass: "text-ellipsis"}, [e.conversation.type === e.TIM.TYPES.CONV_C2C ? s("span", {attrs: {title: e.conversation.userProfile.nick || e.conversation.userProfile.userID}}, [e._v(e._s(e.conversation.userProfile.nick || e.conversation.userProfile.userID) + "\n            ")]) : e.conversation.type === e.TIM.TYPES.CONV_GROUP ? s("span", {attrs: {title: e.conversation.groupProfile.name || e.conversation.groupProfile.groupID}}, [e._v(e._s(e.conversation.groupProfile.name || e.conversation.groupProfile.groupID) + "\n            ")]) : e.conversation.type === e.TIM.TYPES.CONV_SYSTEM ? s("span", [e._v("系统通知\n            ")]) : e._e()])]), s("div", {staticClass: "unread-count"}, [e.showUnreadCount ? s("span", {staticClass: "badge"}, [e._v("\n            " + e._s(e.conversation.unreadCount > 99 ? "99+" : e.conversation.unreadCount) + "\n          ")]) : e._e()])]), s("div", {staticClass: "row-2"}, [s("div", {staticClass: "summary"}, [e.conversation.lastMessage ? s("div", {staticClass: "text-ellipsis"}, [e.hasMessageAtMe ? s("span", {staticClass: "remind"}, [e._v(e._s(e.messageAtMeText))]) : e._e(), s("span", {
                    staticClass: "text",
                    attrs: {title: e.conversation.lastMessage.messageForShow}
                }, [e._v("\n              " + e._s(e.messageForShow) + "\n            ")])]) : e._e()]), s("div", {staticClass: "date"}, [e._v("\n          " + e._s(e.date) + "\n        ")])])])], 1)])
            }, pr = [];

        function hr(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function fr(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? hr(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : hr(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var dr = {
            name: "conversation-item", props: ["conversation"], data: function () {
                return {popoverVisible: !1, showMessageAtMe_text: ""}
            }, computed: fr(fr({
                hasMessageAtMe: function () {
                    return this.currentConversation.conversationID !== this.conversation.conversationID && this.conversation.groupAtInfoList && this.conversation.groupAtInfoList.length > 0
                }, messageAtMeText: function () {
                    var e = this, t = "";
                    return this.conversation.groupAtInfoList.length > 0 && this.conversation.groupAtInfoList.forEach((function (s) {
                        s.atTypeArray[0] === e.TIM.TYPES.CONV_AT_ME && (t = -1 !== t.indexOf("[@所有人]") ? "[@所有人][有人@我]" : "[有人@我]"), s.atTypeArray[0] === e.TIM.TYPES.CONV_AT_ALL && (t = -1 !== t.indexOf("[有人@我]") ? "[有人@我][@所有人]" : "[@所有人]"), s.atTypeArray[0] === e.TIM.TYPES.CONV_AT_ALL_AT_ME && (t = "[@所有人][有人@我]")
                    })), t
                }, showUnreadCount: function () {
                    return (this.$store.getters.hidden || this.currentConversation.conversationID !== this.conversation.conversationID) && this.conversation.unreadCount > 0
                }, date: function () {
                    if (!this.conversation.lastMessage || !this.conversation.lastMessage.lastTime) return "";
                    var e = new Date(1e3 * this.conversation.lastMessage.lastTime);
                    return xe(e) ? Se(e) : Le(e)
                }, avatar: function () {
                    switch (this.conversation.type) {
                        case"GROUP":
                            return this.conversation.groupProfile.avatar;
                        case"C2C":
                            return this.conversation.userProfile.avatar;
                        default:
                            return ""
                    }
                }, conversationName: function () {
                    return this.conversation.type === this.TIM.TYPES.CONV_C2C ? this.conversation.userProfile.nick || this.conversation.userProfile.userID : this.conversation.type === this.TIM.TYPES.CONV_GROUP ? this.conversation.groupProfile.name || this.conversation.groupProfile.groupID : this.conversation.type === this.TIM.TYPES.CONV_SYSTEM ? "系统通知" : ""
                }, showGrayBadge: function () {
                    return this.conversation.type === this.TIM.TYPES.CONV_GROUP && "AcceptNotNotify" === this.conversation.groupProfile.selfInfo.messageRemindType
                }, messageForShow: function () {
                    return this.conversation.lastMessage.isRevoked ? this.conversation.lastMessage.fromAccount === this.currentUserProfile.userID ? "你撤回了一条消息" : this.conversation.type === this.TIM.TYPES.CONV_C2C ? "对方撤回了一条消息" : "".concat(this.conversation.lastMessage.fromAccount, "撤回了一条消息") : this.conversation.lastMessage.messageForShow
                }
            }, Object(H["c"])({
                currentConversation: function (e) {
                    return e.conversation.currentConversation
                }, currentUserProfile: function (e) {
                    return e.user.currentUserProfile
                }
            })), Object(H["b"])(["toAccount"])), mounted: function () {
            }, methods: {
                selectConversation: function () {
                    this.conversation.conversationID !== this.currentConversation.conversationID && this.$store.dispatch("checkoutConversation", this.conversation.conversationID)
                }, deleteConversation: function (e) {
                    var t = this;
                    e.stopPropagation(), this.tim.deleteConversation(this.conversation.conversationID).then((function () {
                        t.$store.commit("showMessage", {
                            message: "会话【".concat(t.conversationName, "】删除成功!"),
                            type: "success"
                        }), t.popoverVisible = !1, t.$store.commit("resetCurrentConversation")
                    })).catch((function (e) {
                        t.$store.commit("showMessage", {
                            message: "会话【".concat(t.conversationName, "】删除失败!, error=").concat(e.message),
                            type: "error"
                        }), t.popoverVisible = !1
                    }))
                }, showContextMenu: function () {
                    this.popoverVisible = !0
                }
            }
        }, gr = dr, vr = (s("7a76"), Object(G["a"])(gr, mr, pr, !1, null, "023923f7", null)), br = vr.exports;

        function Cr(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function yr(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Cr(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Cr(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Or = {
                name: "ConversationList", components: {ConversationItem: br}, data: function () {
                    return {showDialog: !1, userID: "", isCheckouting: !1, timeout: null}
                }, computed: yr({}, Object(H["c"])({
                    conversationList: function (e) {
                        return e.conversation.conversationList
                    }, currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }
                })), mounted: function () {
                    window.addEventListener("keydown", this.handleKeydown)
                }, destroyed: function () {
                    window.removeEventListener("keydown", this.handleKeydown)
                }, methods: {
                    handleRefresh: function () {
                        this.refreshConversation()()
                    }, refreshConversation: function () {
                        var e = this;
                        return function () {
                            e.timeout || (e.timeout = setTimeout((function () {
                                e.timeout = null, e.tim.getConversationList().then((function () {
                                    e.$store.commit("showMessage", {message: "刷新成功", type: "success"})
                                }))
                            }), 1e3))
                        }
                    }, handleAddButtonClick: function () {
                        this.showDialog = !0
                    }, handleConfirm: function () {
                        var e = this;
                        "@TIM#SYSTEM" !== this.userID ? this.$store.dispatch("checkoutConversation", "C2C".concat(this.userID)).then((function () {
                            e.showDialog = !1
                        })).catch((function () {
                            e.$store.commit("showMessage", {message: "没有找到该用户", type: "warning"})
                        })) : this.$store.commit("showMessage", {message: "没有找到该用户", type: "warning"}), this.userID = ""
                    }, handleKeydown: function (e) {
                        var t = this;
                        if (!(38 !== e.keyCode && 40 !== e.keyCode || this.isCheckouting)) {
                            var s = this.conversationList.findIndex((function (e) {
                                return e.conversationID === t.currentConversation.conversationID
                            }));
                            38 === e.keyCode && s - 1 >= 0 && this.checkoutPrev(s), 40 === e.keyCode && s + 1 < this.conversationList.length && this.checkoutNext(s)
                        }
                    }, checkoutPrev: function (e) {
                        var t = this;
                        this.isCheckouting = !0, this.$store.dispatch("checkoutConversation", this.conversationList[e - 1].conversationID).then((function () {
                            t.isCheckouting = !1
                        })).catch((function () {
                            t.isCheckouting = !1
                        }))
                    }, checkoutNext: function (e) {
                        var t = this;
                        this.isCheckouting = !0, this.$store.dispatch("checkoutConversation", this.conversationList[e + 1].conversationID).then((function () {
                            t.isCheckouting = !1
                        })).catch((function () {
                            t.isCheckouting = !1
                        }))
                    }
                }
            }, wr = Or, Ir = (s("f7a2"), Object(G["a"])(wr, lr, ur, !1, null, "4d37341e", null)), Ar = Ir.exports,
            Mr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "list-container"}, [s("div", {staticClass: "header-bar"}, [s("el-autocomplete", {
                    staticClass: "group-seach-bar",
                    attrs: {
                        "value-key": "groupID",
                        debounce: 500,
                        size: "mini",
                        placeholder: "输入群ID搜索",
                        "fetch-suggestions": e.searchGroupByID,
                        "prefix-icon": "el-icon-search",
                        "hide-loading": e.hideSearchLoading
                    },
                    on: {
                        input: function (t) {
                            e.hideSearchLoading = !1
                        }, select: e.applyJoinGroup
                    },
                    model: {
                        value: e.groupID, callback: function (t) {
                            e.groupID = t
                        }, expression: "groupID"
                    }
                }), s("button", {
                    attrs: {title: "创建群组"},
                    on: {click: e.showCreateGroupModel}
                }, [s("i", {staticClass: "tim-icon-add"})])], 1), s("div", {staticClass: "group-container"}, [e._l(e.groupList, (function (e) {
                    return s("group-item", {key: e.groupID, attrs: {group: e}})
                })), s("el-dialog", {
                    attrs: {title: "创建群组", visible: e.createGroupModelVisible, width: "30%"},
                    on: {close: e.closeCreateGroupModel}
                }, [s("create-group")], 1)], 2)])
            }, Pr = [], Tr = (s("3db2"), s("58b8")), Er = s.n(Tr), Dr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", [s("el-form", {
                    ref: "createGroupForm",
                    attrs: {model: e.form, rules: e.rules, "label-width": "100px"}
                }, [s("el-form-item", {attrs: {label: "群ID"}}, [s("el-input", {
                    model: {
                        value: e.form.groupID,
                        callback: function (t) {
                            e.$set(e.form, "groupID", t)
                        },
                        expression: "form.groupID"
                    }
                })], 1), s("el-form-item", {
                    attrs: {
                        label: "群名称",
                        prop: "name"
                    }
                }, [s("el-input", {
                    model: {
                        value: e.form.name, callback: function (t) {
                            e.$set(e.form, "name", t)
                        }, expression: "form.name"
                    }
                })], 1), s("el-form-item", {attrs: {label: "群类型"}}, [s("el-select", {
                    model: {
                        value: e.form.type,
                        callback: function (t) {
                            e.$set(e.form, "type", t)
                        },
                        expression: "form.type"
                    }
                }, [s("el-option", {
                    attrs: {
                        label: "Work",
                        value: e.TIM.TYPES.GRP_WORK
                    }
                }), s("el-option", {
                    attrs: {
                        label: "Public",
                        value: e.TIM.TYPES.GRP_PUBLIC
                    }
                }), s("el-option", {
                    attrs: {
                        label: "Meeting",
                        value: e.TIM.TYPES.GRP_MEETING
                    }
                }), s("el-option", {
                    attrs: {
                        label: "AVChatRoom",
                        value: e.TIM.TYPES.GRP_AVCHATROOM
                    }
                })], 1)], 1), s("el-form-item", {attrs: {label: "群头像地址"}}, [s("el-input", {
                    model: {
                        value: e.form.avatar,
                        callback: function (t) {
                            e.$set(e.form, "avatar", t)
                        },
                        expression: "form.avatar"
                    }
                })], 1), s("el-form-item", {attrs: {label: "群简介"}}, [s("el-input", {
                    attrs: {
                        type: "textarea",
                        maxlength: 240
                    }, model: {
                        value: e.form.introduction, callback: function (t) {
                            e.$set(e.form, "introduction", t)
                        }, expression: "form.introduction"
                    }
                })], 1), s("el-form-item", {attrs: {label: "群公告"}}, [s("el-input", {
                    attrs: {
                        type: "textarea",
                        maxlength: 300
                    }, model: {
                        value: e.form.notification, callback: function (t) {
                            e.$set(e.form, "notification", t)
                        }, expression: "form.notification"
                    }
                })], 1), s("el-form-item", {attrs: {label: "加群方式"}}, [s("el-radio-group", {
                    attrs: {disabled: e.joinOptionDisabled},
                    model: {
                        value: e.form.joinOption, callback: function (t) {
                            e.$set(e.form, "joinOption", t)
                        }, expression: "form.joinOption"
                    }
                }, [s("el-radio", {attrs: {label: "FreeAccess"}}, [e._v("自由加群")]), s("el-radio", {attrs: {label: "NeedPermission"}}, [e._v("需要验证")]), s("el-radio", {attrs: {label: "DisableApply"}}, [e._v("禁止加群")])], 1)], 1), s("el-form-item", {attrs: {label: "群成员列表"}}, [s("el-select", {
                    attrs: {
                        "default-first-option": "",
                        multiple: "",
                        filterable: "",
                        remote: "",
                        disabled: e.form.type === e.TIM.TYPES.GRP_AVCHATROOM,
                        "remote-method": e.handleSearchUser,
                        loading: e.loading,
                        placeholder: "请输入群成员 userID"
                    }, model: {
                        value: e.form.memberList, callback: function (t) {
                            e.$set(e.form, "memberList", t)
                        }, expression: "form.memberList"
                    }
                }, e._l(e.options, (function (e) {
                    return s("el-option", {key: e, attrs: {label: e, value: e}})
                })), 1)], 1)], 1), s("div", {
                    attrs: {slot: "footer"},
                    slot: "footer"
                }, [s("el-button", {
                    attrs: {type: "primary"}, on: {
                        click: function (t) {
                            return e.onSubmit("createGroupForm")
                        }
                    }
                }, [e._v("立即创建")]), s("el-button", {on: {click: e.closeCreateGroupModel}}, [e._v("取消")])], 1)], 1)
            }, _r = [];

        function jr(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Lr(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? jr(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : jr(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Sr = {
            components: {
                ElForm: ne.a,
                ElFormItem: se.a,
                ElInput: w.a,
                ElSelect: ci.a,
                ElOption: oi.a,
                ElRadioGroup: as.a,
                ElRadio: rs.a
            }, data: function () {
                return {
                    form: {
                        groupID: "",
                        name: "",
                        type: this.TIM.TYPES.GRP_WORK,
                        avatar: "",
                        introduction: "",
                        notification: "",
                        joinOption: "FreeAccess",
                        memberList: []
                    }, options: [], loading: !1, rules: {name: [{required: !0, message: "请输入群名称", trigger: "blur"}]}
                }
            }, computed: {
                joinOptionDisabled: function () {
                    return [this.TIM.TYPES.GRP_WORK, this.TIM.TYPES.GRP_MEETING, this.TIM.TYPES.GRP_AVCHATROOM].includes(this.form.type)
                }
            }, methods: {
                onSubmit: function (e) {
                    var t = this;
                    this.$refs[e].validate((function (e) {
                        if (!e) return !1;
                        t.createGroup()
                    }))
                }, closeCreateGroupModel: function () {
                    this.$store.commit("updateCreateGroupModelVisible", !1)
                }, createGroup: function () {
                    var e = this;
                    this.tim.createGroup(this.getOptions()).then((function (t) {
                        e.$store.commit("showMessage", {
                            message: "群组：【".concat(t.data.group.name, "】创建成功"),
                            type: "success"
                        }), e.closeCreateGroupModel()
                    })).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }, getOptions: function () {
                    var e = Lr(Lr({}, this.form), {}, {
                        memberList: this.form.memberList.map((function (e) {
                            return {userID: e}
                        }))
                    });
                    return [this.TIM.TYPES.GRP_WORK, this.TIM.TYPES.GRP_AVCHATROOM].includes(this.form.type) && delete e.joinOption, e
                }, handleSearchUser: function (e) {
                    var t = this;
                    "" !== e && (this.loading = !0, this.tim.getUserProfile({userIDList: [e]}).then((function (e) {
                        var s = e.data;
                        t.options = s.map((function (e) {
                            return e.userID
                        })), t.loading = !1
                    })).catch((function (e) {
                        t.$store.commit("showMessage", {type: "error", message: e.message})
                    })))
                }
            }
        }, kr = Sr, xr = Object(G["a"])(kr, Dr, _r, !1, null, "ee9f0666", null), Rr = xr.exports, Nr = function () {
            var e = this, t = e.$createElement, s = e._self._c || t;
            return s("div", {
                staticClass: "scroll-container",
                on: {click: e.handleGroupClick}
            }, [s("div", {staticClass: "group-item"}, [s("avatar", {attrs: {src: e.group.avatar}}), s("div", {staticClass: "group-name text-ellipsis"}, [e._v(e._s(e.group.name))])], 1)])
        }, Gr = [], Ur = {
            props: ["group"], data: function () {
                return {visible: !1, options: [{text: "退出群组", handler: this.quitGroup}]}
            }, methods: {
                handleGroupClick: function () {
                    var e = "GROUP".concat(this.group.groupID);
                    this.$store.dispatch("checkoutConversation", e)
                }, quitGroup: function () {
                    var e = this;
                    this.tim.quitGroup(this.group.groupID).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }
            }
        }, Vr = Ur, Br = (s("2054"), Object(G["a"])(Vr, Nr, Gr, !1, null, "64a02b1a", null)), Yr = Br.exports;

        function $r(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Fr(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? $r(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : $r(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var qr = {
                data: function () {
                    return {groupID: "", hideSearchLoading: !0}
                },
                components: {GroupItem: Yr, ElDialog: b.a, CreateGroup: Rr, ElAutocomplete: Er.a},
                computed: Fr({
                    groupList: function () {
                        return this.$store.state.group.groupList
                    }
                }, Object(H["c"])({
                    createGroupModelVisible: function (e) {
                        return e.group.createGroupModelVisible
                    }
                })),
                methods: {
                    onGroupUpdated: function (e) {
                        this.$store.dispatch("updateGroupList", e)
                    }, createGroup: function () {
                    }, closeCreateGroupModel: function () {
                        this.$store.commit("updateCreateGroupModelVisible", !1)
                    }, searchGroupByID: function (e, t) {
                        var s = this;
                        e.trim().length > 0 ? (this.hideSearchLoading = !1, this.tim.searchGroupByID(e).then((function (e) {
                            var s = e.data.group;
                            t([s])
                        })).catch((function () {
                            s.$store.commit("showMessage", {message: "没有找到该群", type: "error"})
                        }))) : this.hideSearchLoading = !0
                    }, showCreateGroupModel: function () {
                        this.$store.commit("updateCreateGroupModelVisible", !0)
                    }, applyJoinGroup: function (e) {
                        var t = this;
                        this.tim.joinGroup({groupID: e.groupID}).then(function () {
                            var e = Object(ys["a"])(regeneratorRuntime.mark((function e(s) {
                                return regeneratorRuntime.wrap((function (e) {
                                    while (1) switch (e.prev = e.next) {
                                        case 0:
                                            e.t0 = s.data.status, e.next = e.t0 === t.TIM.TYPES.JOIN_STATUS_WAIT_APPROVAL ? 3 : e.t0 === t.TIM.TYPES.JOIN_STATUS_SUCCESS ? 5 : e.t0 === t.TIM.TYPES.JOIN_STATUS_ALREADY_IN_GROUP ? 9 : 11;
                                            break;
                                        case 3:
                                            return t.$store.commit("showMessage", {
                                                message: "申请成功，等待群管理员确认。",
                                                type: "info"
                                            }), e.abrupt("break", 12);
                                        case 5:
                                            return e.next = 7, t.$store.dispatch("checkoutConversation", "GROUP".concat(s.data.group.groupID));
                                        case 7:
                                            return t.$store.commit("showMessage", {
                                                message: "加群成功",
                                                type: "success"
                                            }), e.abrupt("break", 12);
                                        case 9:
                                            return t.$store.commit("showMessage", {
                                                message: "您已经是群成员了，请勿重复加群哦！",
                                                type: "info"
                                            }), e.abrupt("break", 12);
                                        case 11:
                                            return e.abrupt("break", 12);
                                        case 12:
                                        case"end":
                                            return e.stop()
                                    }
                                }), e)
                            })));
                            return function (t) {
                                return e.apply(this, arguments)
                            }
                        }()).catch((function (e) {
                            t.$store.commit("showMessage", {message: e.message, type: "error"})
                        }))
                    }
                }
            }, Hr = qr, zr = (s("6794"), Object(G["a"])(Hr, Mr, Pr, !1, null, "1b7e0ffd", null)), Qr = zr.exports,
            Jr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "friend-list-container",
                    class: {default: !e.hasFriend}
                }, [e.hasFriend ? s("div", e._l(e.friendList, (function (e) {
                    return s("friend-item", {key: e.userID, attrs: {friend: e}})
                })), 1) : s("div", {staticStyle: {color: "gray"}}, [e._v("暂无好友")])])
            }, Kr = [], Zr = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", [s("el-row", {staticClass: "friend-item-container"}, [s("el-col", {attrs: {span: 6}}, [s("avatar", {attrs: {src: e.friend.profile.avatar}})], 1), s("el-col", {attrs: {span: 18}}, [s("div", {staticClass: "friend-name"}, [e._v(e._s(e.friend.profile.nick || e.friend.userID))])])], 1)], 1)
            }, Xr = [], Wr = {
                props: {friend: {type: Object, required: !0}}, methods: {
                    handleFriendClick: function () {
                        var e = this;
                        this.tim.getConversationProfile("C2C".concat(this.friend.userID)).then((function (t) {
                            var s = t.data;
                            e.$store.commit("updateCurrentConversation", s)
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }
                }
            }, eo = Wr, to = Object(G["a"])(eo, Zr, Xr, !1, null, "0e5cffba", null), so = to.exports;

        function io(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function no(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? io(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : io(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var ro = {
                components: {FriendItem: so}, computed: no(no({}, Object(H["c"])({
                    friendList: function (e) {
                        return e.friend.friendList
                    }
                })), {}, {
                    hasFriend: function () {
                        return this.friendList.length > 0
                    }
                })
            }, oo = ro, ao = (s("47b1"), Object(G["a"])(oo, Jr, Kr, !1, null, null, null)), co = ao.exports,
            lo = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    staticClass: "blacklist-wrapper",
                    class: {default: !e.hasBlacklist}
                }, [e.hasBlacklist ? s("div", e._l(e.blacklist, (function (e) {
                    return s("blacklist-item", {key: e.userID, attrs: {profile: e}})
                })), 1) : s("span", {staticStyle: {color: "gray"}}, [e._v("黑名单还是空的")])])
            }, uo = [], mo = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "blacklist-item-wrapper"}, [s("img", {
                    staticClass: "avatar",
                    attrs: {src: e.profile.avatar ? e.profile.avatar : "http://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png"}
                }), s("div", {staticClass: "item"}, [e._v(e._s(e.profile.nick || e.profile.userID))]), s("el-button", {
                    attrs: {type: "text"},
                    on: {click: e.removeFromBlacklist}
                }, [e._v("取消拉黑")])], 1)
            }, po = [], ho = {
                name: "BlacklistItem",
                props: {profile: {type: Object, required: !0}},
                methods: {
                    removeFromBlacklist: function () {
                        var e = this;
                        this.tim.removeFromBlacklist({userIDList: [this.profile.userID]}).then((function () {
                            e.$store.commit("removeFromBlacklist", e.profile.userID)
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }
                }
            }, fo = ho, go = (s("b849"), Object(G["a"])(fo, mo, po, !1, null, "6763cfe2", null)), vo = go.exports;

        function bo(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Co(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? bo(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : bo(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var yo = {
            name: "Blacklist",
            components: {BlacklistItem: vo},
            computed: Co(Co({}, Object(H["c"])({
                blacklist: function (e) {
                    return e.blacklist.blacklist
                }
            })), {}, {
                hasBlacklist: function () {
                    return this.blacklist.length > 0
                }
            })
        }, Oo = yo, wo = (s("86b5"), Object(G["a"])(Oo, lo, uo, !1, null, "00ad617d", null)), Io = wo.exports;

        function Ao(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Mo(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ao(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ao(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Po = {
                CONVERSATION_LIST: "conversation-list",
                GROUP_LIST: "group-list",
                FRIEND_LIST: "friend-list",
                BLACK_LIST: "black-list",
                GROUP_LIVE: "group-live"
            }, To = {
                name: "SideBar",
                components: {MyProfile: cr, ConversationList: Ar, GroupList: Qr, FriendList: co, BlackList: Io},
                data: function () {
                    return {active: Po.CONVERSATION_LIST, activeName: Po}
                },
                computed: Mo(Mo(Mo({}, Object(H["b"])(["totalUnreadCount"])), Object(H["c"])({
                    userID: function (e) {
                        return e.user.userID
                    }
                })), {}, {
                    showConversationList: function () {
                        return this.active === Po.CONVERSATION_LIST
                    }, showGroupList: function () {
                        return this.active === Po.GROUP_LIST
                    }, showFriendList: function () {
                        return this.active === Po.FRIEND_LIST
                    }, showBlackList: function () {
                        return this.active === Po.BLACK_LIST
                    }, showAddButton: function () {
                        return [Po.CONVERSATION_LIST, Po.GROUP_LIST].includes(this.active)
                    }
                }),
                methods: {
                    checkoutActive: function (e) {
                        this.active = e
                    }, handleClick: function (e) {
                        switch (e.target.id) {
                            case Po.CONVERSATION_LIST:
                                this.checkoutActive(Po.CONVERSATION_LIST);
                                break;
                            case Po.GROUP_LIST:
                                this.checkoutActive(Po.GROUP_LIST);
                                break;
                            case Po.FRIEND_LIST:
                                this.checkoutActive(Po.FRIEND_LIST);
                                break;
                            case Po.BLACK_LIST:
                                this.checkoutActive(Po.BLACK_LIST);
                                break;
                            case Po.GROUP_LIVE:
                                this.groupLive();
                                break
                        }
                    }, handleRefresh: function () {
                        var e = this;
                        switch (this.active) {
                            case Po.CONVERSATION_LIST:
                                this.tim.getConversationList().catch((function (t) {
                                    e.$store.commit("showMessage", {type: "error", message: t.message})
                                }));
                                break;
                            case Po.GROUP_LIST:
                                this.getGroupList();
                                break;
                            case Po.FRIEND_LIST:
                                this.getFriendList();
                                break;
                            case Po.BLACK_LIST:
                                this.$store.dispatch("getBlacklist");
                                break
                        }
                    }, getGroupList: function () {
                        var e = this;
                        this.tim.getGroupList().then((function (t) {
                            var s = t.data;
                            e.$store.dispatch("updateGroupList", s)
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, getFriendList: function () {
                        var e = this;
                        this.tim.getFriendList().then((function (t) {
                            var s = t.data;
                            e.$store.commit("upadteFriendList", s)
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        }))
                    }, groupLive: function () {
                        this.$store.commit("updateGroupLiveInfo", {
                            groupID: 0,
                            anchorID: this.userID
                        }), this.$bus.$emit("open-group-live", {channel: 2})
                    }
                }
            }, Eo = To, Do = (s("04f1"), Object(G["a"])(Eo, zn, Qn, !1, null, "034d1aa8", null)), _o = Do.exports,
            jo = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "login-wrapper"}, [s("img", {
                    staticClass: "logo",
                    attrs: {src: e.logo}
                }), s("el-form", {
                    ref: "login",
                    staticStyle: {width: "100%"},
                    attrs: {rules: e.rules, model: e.form, "label-width": "0"},
                    nativeOn: {
                        keydown: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.submit(t)
                        }
                    }
                }, [s("el-form-item", {attrs: {prop: "userID"}}, [s("el-select", {
                    staticClass: "user-selector",
                    model: {
                        value: e.form.userID, callback: function (t) {
                            e.$set(e.form, "userID", t)
                        }, expression: "form.userID"
                    }
                }, e._l(30, (function (e) {
                    return s("el-option", {key: e, attrs: {label: "user" + (e - 1), value: "user" + (e - 1)}})
                })), 1)], 1)], 1), s("el-button", {
                    staticStyle: {width: "100%", "margin-top": "6px"},
                    attrs: {type: "primary", loading: e.loading},
                    on: {click: e.submit}
                }, [e._v("登录")])], 1)
            }, Lo = [], So = s("a1d8"), ko = s.n(So), xo = {
                name: "Login",
                components: {ElForm: ne.a, ElFormItem: se.a, ElSelect: ci.a, ElOption: oi.a},
                data: function () {
                    var e = function (e, t, s) {
                        /^[a-zA-Z][a-zA-Z0-9_]{3,23}$/.test(t) ? s() : s(new Error("不合法（字母开头+字母/数字，长度4-24)"))
                    };
                    return {
                        form: {userID: "user0", password: ""},
                        rules: {
                            userID: [{required: !0, message: "请输入 userID", trigger: "blur"}, {
                                validator: e,
                                trigger: "blur"
                            }], password: [{required: !0, message: "请输入密码", trigger: "blur"}]
                        },
                        logo: ko.a,
                        registerVisible: !1,
                        loading: !1
                    }
                },
                methods: {
                    submit: function () {
                        var e = this;
                        this.$refs["login"].validate((function (t) {
                            t && e.login()
                        }))
                    }, login: function () {
                        var e = this;
                        this.loading = !0, this.tim.login({
                            userID: this.form.userID,
                            userSig: window.genTestUserSig(this.form.userID).userSig
                        }).then((function () {
                            e.loading = !1, e.$store.commit("toggleIsLogin", !0), e.$store.commit("startComputeCurrent"), e.$store.commit("showMessage", {
                                type: "success",
                                message: "登录成功"
                            }), e.$store.commit({
                                type: "GET_USER_INFO",
                                userID: e.form.userID,
                                userSig: window.genTestUserSig(e.form.userID).userSig,
                                sdkAppID: window.genTestUserSig("").SDKAppID
                            }), e.$store.commit("showMessage", {type: "success", message: "登录成功"})
                        })).catch((function (t) {
                            e.loading = !1, e.$store.commit("showMessage", {message: "登录失败：" + t.message, type: "error"})
                        }))
                    }
                }
            }, Ro = xo, No = (s("ffa6"), Object(G["a"])(Ro, jo, Lo, !1, null, "2fdefeaa", null)), Go = No.exports,
            Uo = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showPreviewer,
                        expression: "showPreviewer"
                    }], staticClass: "image-previewer-wrapper", on: {mousewheel: e.handleMouseWheel}
                }, [s("div", {staticClass: "image-wrapper"}, [s("img", {
                    staticClass: "image-preview",
                    style: {transform: "scale(" + e.zoom + ") rotate(" + e.rotate + "deg)"},
                    attrs: {src: e.previewUrl},
                    on: {click: e.close}
                })]), s("i", {
                    staticClass: "el-icon-close close-button",
                    on: {click: e.close}
                }), s("i", {
                    staticClass: "el-icon-back prev-button",
                    on: {click: e.goPrev}
                }), s("i", {
                    staticClass: "el-icon-right next-button",
                    on: {click: e.goNext}
                }), s("div", {staticClass: "actions-bar"}, [s("i", {
                    staticClass: "el-icon-zoom-out",
                    on: {click: e.zoomOut}
                }), s("i", {
                    staticClass: "el-icon-zoom-in",
                    on: {click: e.zoomIn}
                }), s("i", {
                    staticClass: "el-icon-refresh-left",
                    on: {click: e.rotateLeft}
                }), s("i", {
                    staticClass: "el-icon-refresh-right",
                    on: {click: e.rotateRight}
                }), s("span", {staticClass: "image-counter"}, [e._v(e._s(e.index + 1) + " / " + e._s(e.imgUrlList.length))])])])
            }, Vo = [];

        function Bo(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Yo(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Bo(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Bo(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var $o = {
                name: "ImagePreviewer", data: function () {
                    return {url: "", index: 0, visible: !1, zoom: 1, rotate: 0, minZoom: .1, urlFlag: !1}
                }, computed: Yo(Yo({}, Object(H["b"])(["imgUrlList"])), {}, {
                    showPreviewer: function () {
                        return this.url.length > 0 && this.visible
                    }, imageStyle: function () {
                        return {transform: "scale(".concat(this.zoom, ");")}
                    }, previewUrl: function () {
                        return this.urlFlag ? this.url : this.formatUrl(this.imgUrlList[this.index])
                    }
                }), mounted: function () {
                    this.$bus.$on("image-preview", this.handlePreview)
                }, methods: {
                    handlePreview: function (e) {
                        var t = e.url, s = e.flag, i = void 0 === s ? void 0 : s;
                        this.url = t, this.urlFlag = !!i && "merger", this.index = this.imgUrlList.findIndex((function (e) {
                            return e === t
                        })), this.visible = !0
                    }, handleMouseWheel: function (e) {
                        e.wheelDelta > 0 ? this.zoomIn() : this.zoomOut()
                    }, zoomIn: function () {
                        this.zoom += .1
                    }, zoomOut: function () {
                        this.zoom = this.zoom - .1 > this.minZoom ? this.zoom - .1 : this.minZoom
                    }, close: function () {
                        Object.assign(this, {zoom: 1}), this.visible = !1
                    }, rotateLeft: function () {
                        this.rotate -= 90
                    }, rotateRight: function () {
                        this.rotate += 90
                    }, goNext: function () {
                        this.index = (this.index + 1) % this.imgUrlList.length
                    }, goPrev: function () {
                        this.index = this.index - 1 >= 0 ? this.index - 1 : this.imgUrlList.length - 1
                    }, formatUrl: function (e) {
                        return e ? "//" === e.slice(0, 2) ? "https:".concat(e) : e : ""
                    }
                }
            }, Fo = $o, qo = (s("be86"), Object(G["a"])(Fo, Uo, Vo, !1, null, "b7f38ec6", null)), Ho = qo.exports,
            zo = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "qr-code-list-wrapper"}, [s("div", {staticClass: "header"}, [e._v("\n    其他体验方式 |\n    "), s("a", {
                    staticClass: "link",
                    attrs: {href: "https://cloud.tencent.com/product/im", target: "_blank"},
                    on: {click: e.handlClick}
                }, [e._v("访问官网")])]), e._m(0)])
            }, Qo = [function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "qr-code-wrapper"}, [s("div", {staticClass: "qr-code-item"}, [s("img", {attrs: {src: "https://cloudcache.tencent-cloud.com/open_proj/proj_qcloud_v2/gateway/product/im-new/css/img/applets.png"}}), s("div", {staticClass: "text"}, [e._v("小程序")])]), s("div", {staticClass: "qr-code-item"}, [s("img", {attrs: {src: "https://upload-dianshi-1255598498.file.myqcloud.com/gh_47854f5a3fb8_258-b007e3dc9f40667fdcfcac4c4476c7ce276c7d0e.jpg"}}), s("div", {staticClass: "text"}, [e._v("直播电商解决方案")])]), s("div", {staticClass: "qr-code-item"}, [s("img", {attrs: {src: "https://main.qcloudimg.com/raw/73daa40cef967ba415cdba2cabf4bdc7.png"}}), s("div", {staticClass: "text"}, [e._v("iOS")])]), s("div", {staticClass: "qr-code-item"}, [s("img", {attrs: {src: "https://cloudcache.tencent-cloud.com/open_proj/proj_qcloud_v2/gateway/product/im-new/css/img/android.png"}}), s("div", {staticClass: "text"}, [e._v("Android")])])])
            }], Jo = s("9579"), Ko = s.n(Jo);
        Ko.a.init({
            sid: "500702399",
            cid: "500702403",
            autoReport: 1,
            senseHash: 0,
            senseQuery: 0,
            performanceMonitor: 0,
            ignoreParams: []
        });
        var Zo = Ko.a, Xo = {
                name: "qr-code-list", mounted: function () {
                    Zo.clickStat("link_one", {show: "true"})
                }, methods: {
                    handlClick: function () {
                        Zo.clickStat("link_one", {click: "true"})
                    }
                }
            }, Wo = Xo, ea = (s("3f2d"), Object(G["a"])(Wo, zo, Qo, !1, null, "ea20410a", null)), ta = ea.exports,
            sa = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return e.groupLiveVisible ? s("div", {staticClass: "group-live-mask"}, [s("div", {staticClass: "live-container"}, [s("div", {staticClass: "video-wrap"}, [3 === e.channel && e.userID !== e.anchorID ? [s("live-player")] : [s("live-pusher")]], 2), s("div", {staticClass: "chat-wrap"}, [e.groupLiveVisible ? s("live-chat") : e._e()], 1)])]) : e._e()
            }, ia = [], na = function () {
                var e = this, t = e.$createElement, i = e._self._c || t;
                return i("div", {staticClass: "pusher"}, [i("div", {staticClass: "header-bar"}, [i("live-header", {
                    attrs: {
                        fr: "pusher",
                        pusherTime: e.pusherTime,
                        isPushingStream: e.isPushingStream,
                        stopPushStream: e.stopPushStream
                    }
                }), i("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: !e.isPushingStream,
                        expression: "!isPushingStream"
                    }], staticClass: "input-name-box"
                }, [i("img", {
                    staticClass: "avatar",
                    attrs: {src: e.anchorAvatar, alt: ""}
                }), i("input", {
                    directives: [{
                        name: "model",
                        rawName: "v-model",
                        value: e.roomName,
                        expression: "roomName"
                    }],
                    staticClass: "room-name",
                    attrs: {placeholder: "标题有趣吸引人气"},
                    domProps: {value: e.roomName},
                    on: {
                        input: function (t) {
                            t.target.composing || (e.roomName = t.target.value)
                        }
                    }
                })])], 1), i("div", {
                    staticClass: "video-container",
                    attrs: {id: "video-container"}
                }), i("div", {staticClass: "setting-bar"}, [i("div", [e.isPushingStream ? i("div", {
                    staticClass: "pusher-start cursor",
                    on: {click: e.stopPushStream}
                }, [i("img", {
                    staticClass: "pusher-icon",
                    attrs: {src: s("0270")}
                }), i("span", {staticClass: "play-text"}, [e._v("结束直播")])]) : i("div", {
                    staticClass: "pusher-start cursor",
                    on: {click: e.startPushStream}
                }, [i("img", {
                    staticClass: "pusher-icon",
                    attrs: {src: s("fcad")}
                }), i("span", {staticClass: "play-text"}, [e._v("开始直播")])])]), i("div", [e.isMute ? i("div", {
                    staticClass: "pusher-mic cursor",
                    on: {click: e.startMicrophone}
                }, [i("img", {
                    staticClass: "pusher-icon",
                    attrs: {src: s("c80a")}
                }), i("span", {staticClass: "mic-text"}, [e._v("麦克风")])]) : i("div", {
                    staticClass: "pusher-mic cursor",
                    on: {click: e.stopMicrophone}
                }, [i("img", {
                    staticClass: "pusher-icon",
                    attrs: {src: s("33ad")}
                }), i("span", {staticClass: "mic-text"}, [e._v("麦克风")])])]), i("div", [e.isStartCamera ? i("div", {
                    staticClass: "pusher-mic cursor",
                    staticStyle: {right: "300px"},
                    on: {click: e.startCamera}
                }, [i("svg", {
                    attrs: {
                        width: "16px",
                        height: "16px",
                        viewBox: "0 0 16 16",
                        version: "1.1",
                        xmlns: "http://www.w3.org/2000/svg",
                        "xmlns:xlink": "http://www.w3.org/1999/xlink"
                    }
                }, [i("title", [e._v("摄像头关闭")]), i("g", {
                    attrs: {
                        id: "页面-1",
                        stroke: "none",
                        "stroke-width": "1",
                        fill: "none",
                        "fill-rule": "evenodd"
                    }
                }, [i("g", {
                    attrs: {
                        id: "j进度条备份",
                        transform: "translate(-751.000000, -22.000000)"
                    }
                }, [i("g", {
                    attrs: {
                        id: "编组-9",
                        transform: "translate(751.000000, 22.000000)"
                    }
                }, [i("g", {attrs: {id: "编组-11"}}, [i("path", {
                    attrs: {
                        d: "M15,7.5 C15,11.6421356 11.6421356,15 7.5,15 C6.57050116,15 5.68049488,14.8309122 4.85906245,14.5218179 L6.43561423,12.8970864 C6.77997642,12.9646116 7.13585551,13 7.5,13 C10.5375661,13 13,10.5375661 13,7.5 C13,7.0789865 12.9526952,6.66902162 12.8631108,6.27513055 L14.4397563,4.65055194 C14.8008626,5.52907797 15,6.49128347 15,7.5 Z M7.5,0 C9.88955323,0 12.0181001,1.11750106 13.3915381,2.85840064 L11.9813778,4.31063973 C10.984196,2.91201268 9.34860401,2 7.5,2 C4.46243388,2 2,4.46243388 2,7.5 C2,9.40763139 2.97118511,11.0884303 4.44609208,12.0749336 L3.03637255,13.5276986 C1.1940301,12.1611583 0,9.97001947 0,7.5 C0,3.35786438 3.35786438,0 7.5,0 Z",
                        id: "形状结合",
                        fill: "#8A9099",
                        "fill-rule": "nonzero"
                    }
                }), i("path", {
                    attrs: {
                        d: "M7.5,5 C8.54350703,5 9.43769004,5.63933214 9.81221332,6.5476607 L6.61637488,9.83935679 C5.67174144,9.48236445 5,8.56962904 5,7.5 C5,6.11928813 6.11928813,5 7.5,5 Z",
                        id: "形状结合",
                        fill: "#8A9099"
                    }
                }), i("path", {
                    attrs: {
                        d: "",
                        id: "形状结合",
                        stroke: "#8A9099",
                        "stroke-width": "2"
                    }
                }), i("line", {
                    attrs: {
                        x1: "13.3137085",
                        y1: "2",
                        x2: "2",
                        y2: "13.3137085",
                        id: "直线-5",
                        stroke: "#8A9099",
                        "stroke-width": "2",
                        "stroke-linecap": "square"
                    }
                })])])])])]), i("span", {staticClass: "mic-text"}, [e._v("摄像头")])]) : i("div", {
                    staticClass: "pusher-mic cursor",
                    staticStyle: {right: "300px"},
                    on: {click: e.stopCamera}
                }, [i("img", {
                    staticClass: "pusher-icon",
                    attrs: {src: s("aa8b")}
                }), i("span", {staticClass: "mic-text"}, [e._v("摄像头")])])])])])
            }, ra = [], oa = function () {
                var e = this, t = e.$createElement, i = e._self._c || t;
                return i("div", {staticClass: "header-container"}, [i("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showLiveInfo,
                        expression: "showLiveInfo"
                    }]
                }, [i("div", {staticClass: "anchor-info"}, [i("img", {
                    staticClass: "anchor-avatar",
                    attrs: {src: e.avatar}
                }), i("div", {staticClass: "anchor-other"}, [i("p", {staticClass: "anchor-nick"}, [e._v(e._s(e.nick))]), i("p", {staticClass: "online-num"}, [e._v("在线：" + e._s(e.onlineMemberCount))])])]), i("div", {staticClass: "online-info"}, [i("p", {staticClass: "room-name"}, [e._v("直播中")]), i("img", {
                    staticClass: "living-icon",
                    attrs: {src: s("406d")}
                }), i("span", [e._v(e._s(" " + e.pusherTime))])])]), i("div", {
                    staticClass: "close-box",
                    on: {click: e.closeLiveMask}
                }, [i("i", {staticClass: "el-icon-circle-close"})])])
            }, aa = [];

        function ca(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function la(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? ca(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : ca(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var ua = {
            name: "liveHeader",
            props: {
                fr: {type: String, requred: !0},
                isPushingStream: {type: Boolean, default: !1},
                stopPushStream: {type: Function},
                pusherTime: {type: String, default: ""}
            },
            data: function () {
                return {
                    nick: "",
                    avatar: "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png",
                    onlineMemberCount: 0,
                    timer: null,
                    onlineList: []
                }
            },
            computed: la(la({}, Object(H["c"])({
                groupLiveInfo: function (e) {
                    return e.groupLive.groupLiveInfo
                }
            })), {}, {
                showLiveInfo: function () {
                    return "pusher" === this.fr && this.isPushingStream || "player" === this.fr
                }, roomName: function () {
                    return this.groupLiveInfo.roomName || "".concat(this.groupLiveInfo.anchorID, "的直播")
                }
            }),
            mounted: function () {
                var e = this;
                this.getAnchorProfile(), "player" === this.fr && (this.timer = setInterval((function () {
                    e.getGroupOnlineMemberCount()
                }), 5e3))
            },
            beforeDestroy: function () {
                this.timer && clearInterval(this.timer)
            },
            methods: {
                closeLiveMask: function () {
                    "pusher" !== this.fr ? (this.$store.commit("updateGroupLiveInfo", {isNeededQuitRoom: 1}), this.$bus.$emit("close-group-live")) : this.stopPushStream()
                }, getAnchorProfile: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                        var t;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    return e.next = 2, this.tim.getUserProfile({userIDList: [this.groupLiveInfo.anchorID]});
                                case 2:
                                    t = e.sent, 0 === t.code && (this.nick = t.data[0].nick || t.data[0].userID, this.avatar = t.data[0].avatar || "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png");
                                case 4:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t() {
                        return e.apply(this, arguments)
                    }

                    return t
                }(), getGroupOnlineMemberCount: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                        var t;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    return e.next = 2, this.tim.getGroupOnlineMemberCount(this.groupLiveInfo.roomID);
                                case 2:
                                    t = e.sent, 0 === t.code && t.data && (this.onlineMemberCount = t.data.memberCount);
                                case 4:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t() {
                        return e.apply(this, arguments)
                    }

                    return t
                }()
            },
            watch: {
                isPushingStream: function (e) {
                    var t = this;
                    e && "pusher" === this.fr && (this.timer = setInterval((function () {
                        t.getGroupOnlineMemberCount()
                    }), 5e3))
                }
            }
        }, ma = ua, pa = (s("a328"), Object(G["a"])(ma, oa, aa, !1, null, "3e144eae", null)), ha = pa.exports;

        function fa(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function da(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? fa(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : fa(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var ga = {
                name: "livePusher", data: function () {
                    return {
                        pusher: null,
                        roomID: 0,
                        roomName: "",
                        isPushingStream: !1,
                        updateTimer: 0,
                        pusherTime: "00:00:00",
                        time: 0,
                        recordTimer: null,
                        isMute: !1,
                        isStartCamera: !0
                    }
                }, computed: da(da({}, Object(H["c"])({
                    user: function (e) {
                        return e.user
                    }, groupLiveInfo: function (e) {
                        return e.groupLive.groupLiveInfo
                    }
                })), {}, {
                    anchorAvatar: function () {
                        return this.user.currentUserProfile.avatar || "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png"
                    }
                }), created: function () {
                    this.$store.commit("resetGroupLiveInfo", {roomID: 0})
                }, mounted: function () {
                    this.init()
                }, beforeDestroy: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    if (!this.isPushingStream) {
                                        e.next = 5;
                                        break
                                    }
                                    return clearInterval(this.updateTimer), clearInterval(this.recordTimer), e.next = 5, this.stopPush();
                                case 5:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t() {
                        return e.apply(this, arguments)
                    }

                    return t
                }(), components: {liveHeader: ha}, methods: {
                    init: function () {
                        this.pusher = this.TWebLive.createPusher({userID: this.user.userID}), this.setRenderView(), this.pusher.on(this.TWebLive.EVENT.RTC_CONNECTION_STATE_CHANGED, this.onRTCConnectionStateChanged), this.pusher.on(this.TWebLive.EVENT.RTC_CLIENT_BANNED, this.onRTCClientBanned), this.pusher.on(this.TWebLive.EVENT.RTC_CLIENT_ERROR, this.onRTCError)
                    }, onRTCConnectionStateChanged: function (e) {
                    }, onRTCClientBanned: function (e) {
                    }, onRTCError: function (e) {
                    }, setRenderView: function () {
                        var e = this;
                        this.pusher.setRenderView({elementID: "video-container", audio: !0, video: !0}).then((function () {
                            var t = window.document.getElementById("video-container").childNodes;
                            t[0].style.backgroundColor = "rgba(0,0,0,0)", e.isStartCamera = !1
                        })).catch((function () {
                        }))
                    }, startCamera: function () {
                        var e = this;
                        this.pusher.startCamera().then((function () {
                            e.isStartCamera = !1
                        })).catch((function () {
                        }))
                    }, stopCamera: function () {
                        var e = this;
                        this.pusher.stopCamera().then((function () {
                            e.isStartCamera = !0
                        })).catch((function () {
                        }))
                    }, startMicrophone: function () {
                        var e = this;
                        this.pusher.startMicrophone().then((function () {
                            e.isMute = !1
                        })).catch((function () {
                        }))
                    }, stopMicrophone: function () {
                        var e = this;
                        this.pusher.stopMicrophone().then((function () {
                            e.isMute = !0
                        })).catch((function () {
                        }))
                    }, generateRoomID: function (e, t) {
                        return Math.floor(Math.random() * (t - e) + e).toString()
                    }, createRoom: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        return this.roomID = this.generateRoomID(1e3, 2e9), this.roomName = this.roomName ? this.roomName : "".concat(this.user.userID, "的直播"), e.next = 4, ws()("https://service-62h5r0ea-1252463788.gz.apigw.tencentcs.com/release/forTestAdvanced?method=createRoom&appId=".concat(this.user.sdkAppID, "&type=groupLive&title=").concat(this.roomName, "&anchorId=").concat(this.user.userID, "&roomId=").concat(this.roomID));
                                    case 4:
                                        this.$store.commit("updateGroupLiveInfo", {
                                            roomID: this.roomID,
                                            roomName: this.roomName
                                        }), this.createGroupLiveAvChatRoom();
                                    case 6:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this)
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), destroyRoom: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        return e.next = 2, ws()("https://service-c2zjvuxa-1252463788.gz.apigw.tencentcs.com/release/forTest?method=destroyRoom&appId=".concat(this.user.sdkAppID, "&type=groupLive&roomId=").concat(this.roomID));
                                    case 2:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this)
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), updateRoom: function () {
                        ws()("https://service-c2zjvuxa-1252463788.gz.apigw.tencentcs.com/release/forTest?method=updateRoom&appId=".concat(this.user.sdkAppID, "&type=groupLive&roomId=").concat(this.roomID))
                    }, createGroupLiveAvChatRoom: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        return e.next = 2, this.tim.createGroup({
                                            name: this.roomName,
                                            groupID: this.roomID,
                                            type: this.TIM.TYPES.GRP_AVCHATROOM
                                        });
                                    case 2:
                                        this.$bus.$emit("join-group-live-avchatroom");
                                    case 3:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this)
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), startPushStream: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            var t, s, i = this;
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        return e.next = 2, this.createRoom();
                                    case 2:
                                        t = "".concat(this.user.sdkAppID, "_").concat(this.roomID, "_").concat(this.user.userID, "_main"), s = "room://livedomainname=tuikit.qcloud.com&sdkappid=".concat(this.user.sdkAppID, "&roomid=").concat(this.roomID, "&userid=").concat(this.user.userID, "&usersig=").concat(encodeURIComponent(this.user.userSig), "&streamid=").concat(t), this.pusher.startPush(s).then((function () {
                                            i.isPushingStream = !0, i.sendNoticeToGroup(1), i.updateTimer = setInterval((function () {
                                                i.updateRoom()
                                            }), 1e4), i.recordTimer = setInterval((function () {
                                                i.recordLiveTime()
                                            }), 1e3)
                                        })).catch((function () {
                                        }));
                                    case 5:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this)
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), stopPushStream: function () {
                        this.$bus.$emit("close-group-live")
                    }, stopPush: function () {
                        var e = Object(ys["a"])(regeneratorRuntime.mark((function e() {
                            return regeneratorRuntime.wrap((function (e) {
                                while (1) switch (e.prev = e.next) {
                                    case 0:
                                        return e.next = 2, this.destroyRoom();
                                    case 2:
                                        return e.next = 4, this.pusher.stopPush();
                                    case 4:
                                        return e.next = 6, this.tim.dismissGroup(this.roomID);
                                    case 6:
                                        this.isPushingStream = !1, this.sendNoticeToGroup(0);
                                    case 8:
                                    case"end":
                                        return e.stop()
                                }
                            }), e, this)
                        })));

                        function t() {
                            return e.apply(this, arguments)
                        }

                        return t
                    }(), sendNoticeToGroup: function (e) {
                        if (this.groupLiveInfo.groupID) {
                            var t = this.user.currentUserProfile, s = t.userID, i = t.nick, n = t.avatar, r = {
                                roomId: this.roomID,
                                roomName: this.roomName,
                                roomCover: n,
                                roomStatus: "".concat(e),
                                anchorName: i,
                                version: 4,
                                roomType: "liveRoom",
                                anchorId: s,
                                businessID: "group_live"
                            }, o = this.tim.createCustomMessage({
                                to: this.groupLiveInfo.groupID,
                                conversationType: this.TIM.TYPES.CONV_GROUP,
                                priority: this.TIM.TYPES.MSG_PRIORITY_NORMAL,
                                payload: {data: JSON.stringify(r), description: "", extension: ""}
                            });
                            this.$store.commit("pushCurrentMessageList", o), this.tim.sendMessage(o).then((function () {
                            })).catch((function () {
                            }))
                        }
                    }, recordLiveTime: function () {
                        this.time++, this.pusherTime = Ne(this.time)
                    }
                }
            }, va = ga, ba = (s("464f"), Object(G["a"])(va, na, ra, !1, null, "1dfd203c", null)), Ca = ba.exports,
            ya = function () {
                var e = this, t = e.$createElement, i = e._self._c || t;
                return i("div", {staticClass: "player"}, [i("div", {staticClass: "header-bar"}, [i("live-header", {attrs: {fr: "player"}})], 1), e._m(0), i("div", {staticClass: "setting-bar"}, [i("div", [e.isPlaying ? i("div", {
                    staticClass: "player-start cursor",
                    on: {click: e.pauseVideo}
                }, [i("img", {
                    staticClass: "player-icon",
                    attrs: {src: s("7412")}
                }), i("span", {staticClass: "play-text"}, [e._v("暂停观看")])]) : i("div", {
                    staticClass: "player-start cursor",
                    on: {click: e.resumeVideo}
                }, [i("img", {
                    staticClass: "player-icon",
                    attrs: {src: s("fcad")}
                }), i("span", {staticClass: "play-text"}, [e._v("观看直播")])])]), i("div", {
                    ref: "volumeBox",
                    staticClass: "volume-box"
                }, [i("p", {
                    staticClass: "setting-icon cursor ",
                    on: {click: e.setPlayoutVolume}
                }, [i("svg", {
                    attrs: {
                        width: "16",
                        height: "16",
                        viewBox: "0 0 16 16",
                        fill: "none",
                        xmlns: "http://www.w3.org/2000/svg"
                    }
                }, [i("mask", {
                    attrs: {
                        id: "path-1-inside-1",
                        fill: "white"
                    }
                }, [i("path", {
                    attrs: {
                        "fill-rule": "evenodd",
                        "clip-rule": "evenodd",
                        d: "M4.18171 12H0V4H4.19181L8 0.831913L9 0V1.3008V14.7003V16L8 15.1698L4.18171 12ZM5 4.62845L8 2.13271V13.8701L5 11.3796V4.62845Z"
                    }
                })]), i("path", {
                    attrs: {
                        d: "M0 12V14H-2V12H0ZM4.18171 12V10H4.9037L5.45921 10.4612L4.18171 12ZM0 4H-2V2H0V4ZM4.19181 4L5.47089 5.53752L4.91496 6H4.19181V4ZM8 0.831913L6.72092 -0.705603V-0.705603L8 0.831913ZM9 0L7.72092 -1.53752L11 -4.26543V0H9ZM9 16H11V20.2597L7.72251 17.5388L9 16ZM8 15.1698L9.27749 13.631H9.27749L8 15.1698ZM5 4.62845H3V3.69068L3.72092 3.09094L5 4.62845ZM8 2.13271L6.72092 0.595197L10 -2.13271V2.13271H8ZM8 13.8701H10V18.1299L6.72251 15.409L8 13.8701ZM5 11.3796L3.72251 12.9185L3 12.3187V11.3796H5ZM0 10H4.18171V14H0V10ZM2 4V12H-2V4H2ZM4.19181 6H0V2H4.19181V6ZM2.91273 2.46248L6.72092 -0.705603L9.27908 2.36943L5.47089 5.53752L2.91273 2.46248ZM6.72092 -0.705603L7.72092 -1.53752L10.2791 1.53752L9.27908 2.36943L6.72092 -0.705603ZM11 0V1.3008H7V0H11ZM11 1.3008V14.7003H7V1.3008H11ZM11 14.7003V16H7V14.7003H11ZM7.72251 17.5388L6.72251 16.7087L9.27749 13.631L10.2775 14.4612L7.72251 17.5388ZM6.72251 16.7087L2.90422 13.5388L5.45921 10.4612L9.27749 13.631L6.72251 16.7087ZM3.72092 3.09094L6.72092 0.595197L9.27908 3.67023L6.27908 6.16597L3.72092 3.09094ZM10 2.13271V13.8701H6V2.13271H10ZM6.72251 15.409L3.72251 12.9185L6.27749 9.8408L9.27749 12.3313L6.72251 15.409ZM3 11.3796V4.62845H7V11.3796H3Z",
                        fill: "#8A9099",
                        mask: "url(#path-1-inside-1)"
                    }
                }), i("path", {
                    attrs: {
                        "fill-rule": "evenodd",
                        "clip-rule": "evenodd",
                        d: "M11.6088 13.9991C13.9991 13.0388 15.6868 10.6992 15.6868 7.9654C15.6868 5.24314 14.0133 2.91174 11.639 1.94385L10.9332 3.81684C12.5511 4.49874 13.6868 6.09944 13.6868 7.9654C13.6868 9.84413 12.5355 11.454 10.8999 12.1278L11.6088 13.9991Z",
                        fill: "#8A9099"
                    }
                })]), i("span", {staticClass: "mic-text"}, [e._v("声音")])]), i("span", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.showSetVolume,
                        expression: "showSetVolume"
                    }], ref: "progressBox", staticClass: "progress-box"
                }, [i("el-slider", {
                    attrs: {vertical: "", height: "200px"},
                    on: {change: e.setPlayoutVolume},
                    model: {
                        value: e.volumeValue, callback: function (t) {
                            e.volumeValue = t
                        }, expression: "volumeValue"
                    }
                })], 1)])])])
            }, Oa = [function () {
                var e = this, t = e.$createElement, i = e._self._c || t;
                return i("div", {
                    staticClass: "video-container",
                    attrs: {id: "player-container"}
                }, [i("div", {staticStyle: {position: "absolute"}}, [i("img", {attrs: {src: s("3dbe")}}), i("p", {
                    staticStyle: {
                        "font-size": "24px",
                        color: "#8A9099",
                        "text-align": "center",
                        "margin-top": "30px"
                    }
                }, [e._v("暂无画面")])])])
            }], wa = (s("b5c2"), s("20cf")), Ia = s.n(wa), Aa = s("9b3f"), Ma = s.n(Aa);

        function Pa(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Ta(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Pa(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Pa(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Ea = {
                name: "livePlayer", data: function () {
                    return {player: null, isPlaying: !0, volumeValue: 70, showSetVolume: !1, isMute: !1}
                }, computed: Ta({}, Object(H["c"])({
                    user: function (e) {
                        return e.user
                    }, roomID: function (e) {
                        return e.groupLive.groupLiveInfo.roomID
                    }, anchorID: function (e) {
                        return e.groupLive.groupLiveInfo.anchorID
                    }
                })), mounted: function () {
                    var e = this;
                    this.init();
                    var t = this.$refs.volumeBox, s = this.$refs.progressBox;
                    s.addEventListener("mouseover", (function () {
                        e.showSetVolume = !0
                    })), s.addEventListener("mouseout", (function () {
                        e.showSetVolume = !1
                    })), t.addEventListener("mouseover", (function () {
                        e.showSetVolume = !0
                    })), t.addEventListener("mouseout", (function () {
                        e.showSetVolume = !1
                    }))
                }, beforeDestroy: function () {
                    this.stopPlay()
                }, components: {liveHeader: ha, elSlider: Ia.a}, methods: {
                    init: function () {
                        this.player = this.TWebLive.createPlayer(), this.player.setCustomConfig({
                            autoplay: !0,
                            poster: {style: "cover", src: Ma.a},
                            pausePosterEnabled: !1,
                            wording: {
                                1: "您观看的直播已结束哦~ ",
                                2: "您观看的直播已结束哦~ ",
                                4: "您观看的直播已结束哦~ ",
                                13: "您观看的直播已结束",
                                2032: "请求视频失败，请检查网络",
                                2048: "请求m3u8文件失败，可能是网络错误或者跨域问题"
                            }
                        }), this.player.on(this.TWebLive.EVENT.PLAYER_PLAYING, this.onPlayerPlaying), this.player.on(this.TWebLive.EVENT.PLAYER_PAUSE, this.onPlayerPause), this.player.on(this.TWebLive.EVENT.PLAYER_AUTOPLAY_NOT_ALLOWED, this.onPlayerAutoPlayNotAllowed), this.player.on(this.TWebLive.EVENT.PLAYER_ERROR, this.onPlayerError), this.setRenderView(), this.$bus.$emit("join-group-live-avchatroom")
                    }, onPlayerPlaying: function (e) {
                    }, onPlayerPause: function (e) {
                    }, onPlayerAutoPlayNotAllowed: function (e) {
                        this.$store.commit("showMessage", {message: "不能自动播放", type: "info"})
                    }, onPlayerError: function (e) {
                    }, setRenderView: function () {
                        this.player.setRenderView({elementID: "player-container"}), this.startPlay()
                    }, startPlay: function () {
                        var e = this,
                            t = "".concat(this.user.sdkAppID, "_").concat(this.roomID, "_").concat(this.anchorID, "_main"),
                            s = "https://tuikit.qcloud.com/live/".concat(t, ".flv"),
                            i = "https://tuikit.qcloud.com/live/".concat(t, ".m3u8"),
                            n = "https://flv=".concat(encodeURIComponent(s), "&hls=").concat(encodeURIComponent(i));
                        this.player.startPlay(n).then((function () {
                            e.isPlaying = !0
                        })).catch((function () {
                        }))
                    }, resumeAudio: function () {
                        var e = this;
                        this.player.resumeAudio().then((function () {
                            e.isMute = !1
                        })).catch((function () {
                        }))
                    }, pauseAudio: function () {
                        var e = this;
                        this.player.pauseAudio().then((function () {
                            e.isMute = !0
                        })).catch((function () {
                        }))
                    }, pauseVideo: function () {
                        var e = this;
                        this.player.pauseVideo().then((function () {
                            e.isPlaying = !1
                        })).catch((function () {
                        }))
                    }, resumeVideo: function () {
                        var e = this;
                        this.player.resumeVideo().then((function () {
                            e.isPlaying = !0
                        })).catch((function () {
                        }))
                    }, setPlayoutVolume: function () {
                        this.player.setPlayoutVolume(this.volumeValue)
                    }, stopPlay: function () {
                        this.player.stopPlay(), this.isPlaying = !1
                    }
                }
            }, Da = Ea, _a = (s("1474"), s("4cb9"), Object(G["a"])(Da, ya, Oa, !1, null, "6ab90d2e", null)),
            ja = _a.exports, La = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "comment-wrapper"}, [s("div", {
                    ref: "message-list",
                    staticClass: "message-list"
                }, [e._l(e.avChatRoomMessageList, (function (t, i) {
                    return ["TIMGroupTipElem" === t.type && 1 === t.payload.groupJoinType ? [s("div", {
                        key: "join_msg_item_" + i,
                        staticClass: "msg-item-join"
                    }, [e._v(e._s("欢迎" + (t.nick || t.payload.userIDList.join("")) + "进入直播间"))])] : e._e(), "TIMGroupTipElem" === t.type && 0 === t.payload.groupJoinType ? [s("div", {
                        key: "leave_msg_item_" + i,
                        staticClass: "msg-item-join"
                    }, [e._v(e._s((t.nick || t.payload.userIDList.join("")) + "离开了直播间"))])] : e._e(), "TIMTextElem" === t.type ? [s("div", {
                        key: "text_msg_item_" + i,
                        staticClass: "msg-item-text"
                    }, [s("img", {
                        staticClass: "avatar",
                        attrs: {src: e.getAvatar(t), alt: ""}
                    }), s("p", {staticClass: "nick"}, [e._v(e._s(e.getNick(t)))]), s("p", {staticClass: "msg"}, [s("span", {staticClass: "msg-text"}, [e._v(e._s(t.payload.text))])])])] : e._e(), "TIMCustomElem" === t.type ? ["5" === t.payload.data.command ? [s("div", {
                        key: "barrage_msg_item_" + i,
                        staticClass: "msg-item-text"
                    }, [s("img", {
                        staticClass: "avatar",
                        attrs: {src: e.getAvatar(t), alt: ""}
                    }), s("p", {staticClass: "nick"}, [e._v(e._s(e.getNick(t)))]), s("p", {staticClass: "msg"}, [s("span", {staticClass: "msg-text"}, [e._v(e._s(t.payload.data.message))])])])] : e._e(), "6" === t.payload.data.command ? [s("div", {
                        key: "gift_msg_item_" + i,
                        staticClass: "msg-item-gift"
                    }, [s("img", {
                        staticClass: "avatar",
                        attrs: {src: e.getAvatar(t), alt: ""}
                    }), s("p", {staticClass: "nick"}, [e._v(e._s(e.getNick(t)))]), s("p", {staticClass: "msg"}, [e._v("送了一个" + e._s(e.giftInfo[t.payload.data.message - 1].name))]), s("img", {
                        staticClass: "gift-pic",
                        attrs: {src: e.giftInfo[t.payload.data.message - 1].icon, alt: ""}
                    })])] : e._e()] : e._e()]
                }))], 2), e.isAnchor ? e._e() : s("live-gift"), s("div", {staticClass: "send-container"}, [s("textarea", {
                    directives: [{
                        name: "model",
                        rawName: "v-model",
                        value: e.messageContent,
                        expression: "messageContent"
                    }],
                    staticClass: "comment-message",
                    attrs: {placeholder: e.sendAvailable ? "请输入内容..." : "开始直播后可以互动聊天哦！", disabled: !e.sendAvailable},
                    domProps: {value: e.messageContent},
                    on: {
                        keyup: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.sendMessage(t)
                        }, input: function (t) {
                            t.target.composing || (e.messageContent = t.target.value)
                        }
                    }
                }), s("el-tooltip", {
                    staticClass: "item",
                    attrs: {effect: "dark", content: "按Enter发送消息", placement: "left-start"}
                }, [s("div", {
                    staticClass: "btn-send",
                    on: {click: e.sendMessage}
                }, [s("div", {staticClass: "tim-icon-send"})])])], 1)], 1)
            }, Sa = [], ka = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {staticClass: "chat-footer-container"}, [s("carousel", {
                    attrs: {
                        autoplay: !1,
                        loop: !1,
                        "initial-index": 1,
                        "indicator-position": "none",
                        arrow: "always"
                    }
                }, e._l(e.giftList, (function (t, i) {
                    return s("carousel-item", {key: "item_" + i}, [e._l(t, (function (t, n) {
                        return [s("div", {
                            key: "gift_item_" + i + "_" + n,
                            staticClass: "gift-item",
                            on: {
                                click: function (s) {
                                    return e.handleGiftPic(t.index)
                                }
                            }
                        }, [s("img", {
                            staticClass: "gift-icon",
                            attrs: {src: t.icon, alt: ""}
                        }), s("p", {staticClass: "gift-name"}, [e._v(e._s(t.name))])])]
                    }))], 2)
                })), 1)], 1)
            }, xa = [], Ra = (s("186a"), s("301f")), Na = s.n(Ra), Ga = (s("96dc"), s("9cea")), Ua = s.n(Ga), Va = {
                name: "liveGift", props: {}, data: function () {
                    return {
                        giftList: [[{
                            index: 1,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482989_25.png",
                            name: "火箭"
                        }, {
                            index: 2,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1507876726_3",
                            name: "鸡蛋"
                        }, {
                            index: 3,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482294_7.png",
                            name: "吻"
                        }], [{
                            index: 4,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482461_11.png",
                            name: "跑车"
                        }, {
                            index: 5,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1594714453_7.png",
                            name: "嘉年华"
                        }, {
                            index: 6,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482754_17.png",
                            name: "玫瑰"
                        }], [{
                            index: 7,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1594281297_11.png",
                            name: "直升机"
                        }, {
                            index: 8,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1507876472_1",
                            name: "点赞"
                        }, {
                            index: 9,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483038_27.png",
                            name: "比心"
                        }], [{
                            index: 10,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483168_31.png",
                            name: "冰淇淋"
                        }, {
                            index: 11,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483225_33.png",
                            name: "玩偶"
                        }, {
                            index: 12,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483278_35.png",
                            name: "蛋糕"
                        }], [{
                            index: 13,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483348_37.png",
                            name: "豪华轿车"
                        }, {
                            index: 14,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483429_39.png",
                            name: "游艇"
                        }, {
                            index: 15,
                            icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483505_41.png",
                            name: "翅膀"
                        }]]
                    }
                }, components: {Carousel: Ua.a, CarouselItem: Na.a}, methods: {
                    handleGiftPic: function (e) {
                        this.$bus.$emit("group-live-send-gift", e)
                    }
                }
            }, Ba = Va, Ya = (s("32c9"), s("0e57"), Object(G["a"])(Ba, ka, xa, !1, null, "10dee1ca", null)),
            $a = Ya.exports;

        function Fa(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function qa(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Fa(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Fa(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Ha = {
            name: "liveChat", data: function () {
                return {
                    sendAvailable: !1,
                    messageContent: "",
                    defaultAvatar: "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-2.png",
                    giftInfo: [{
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482989_25.png",
                        name: "火箭"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1507876726_3",
                        name: "鸡蛋"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482294_7.png",
                        name: "吻"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482461_11.png",
                        name: "跑车"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1594714453_7.png",
                        name: "嘉年华"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590482754_17.png",
                        name: "玫瑰"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1594281297_11.png",
                        name: "直升机"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1507876472_1",
                        name: "点赞"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483038_27.png",
                        name: "比心"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483168_31.png",
                        name: "冰淇淋"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483225_33.png",
                        name: "玩偶"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483278_35.png",
                        name: "蛋糕"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483348_37.png",
                        name: "豪华轿车"
                    }, {
                        icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483429_39.png",
                        name: "游艇"
                    }, {icon: "https://8.url.cn/huayang/resource/now/new_gift/1590483505_41.png", name: "翅膀"}]
                }
            }, computed: qa(qa({}, Object(H["c"])({
                user: function (e) {
                    return e.user
                }, groupLiveInfo: function (e) {
                    return e.groupLive.groupLiveInfo
                }, avChatRoomMessageList: function (e) {
                    var t = e.groupLive.avChatRoomMessageList;
                    return t.map((function (e) {
                        return "TIMCustomElem" === e.type && "string" === typeof e.payload.data && e.payload.data.indexOf("{") > -1 && (e.payload.data = JSON.parse(e.payload.data)), e
                    })), t
                }
            })), {}, {
                isAnchor: function () {
                    return this.user.userID === this.groupLiveInfo.anchorID
                }
            }), components: {liveGift: $a, ElTooltip: ee.a}, created: function () {
                var e = this;
                this.$bus.$on("join-group-live-avchatroom", (function () {
                    e.joinGroupLiveAvChatRoom()
                }))
            }, mounted: function () {
                var e = this;
                this.$bus.$on("group-live-send-gift", (function (t) {
                    var s = e.tim.createCustomMessage({
                        to: e.groupLiveInfo.roomID,
                        conversationType: e.TIM.TYPES.CONV_GROUP,
                        payload: {
                            data: JSON.stringify({
                                version: "1.0.0",
                                message: "".concat(t),
                                command: "6",
                                action: 301
                            }), description: "", extension: ""
                        }
                    });
                    e.$store.commit("pushAvChatRoomMessageList", JSON.parse(JSON.stringify(s))), e.tim.sendMessage(s).catch((function (t) {
                        e.$store.commit("showMessage", {type: "error", message: t.message})
                    }))
                }))
            }, beforeDestroy: function () {
                this.$bus.$off("join-group-live-avchatroom"), this.$bus.$off("group-live-send-gift"), this.isAnchor || 1 !== this.groupLiveInfo.isNeededQuitRoom || this.quitGroupLiveAvChatRoom(), this.$store.commit("updateGroupLiveInfo", {isNeededQuitRoom: 0})
            }, methods: {
                getAvatar: function (e) {
                    return e.from === this.user.userID ? this.user.currentUserProfile.avatar || this.defaultAvatar : e.avatar || this.defaultAvatar
                }, getNick: function (e) {
                    return e.from === this.user.userID ? this.user.currentUserProfile.nick || e.from : e.nick || e.from
                }, joinGroupLiveAvChatRoom: function () {
                    var e = this;
                    this.tim.joinGroup({groupID: this.groupLiveInfo.roomID}).then((function (t) {
                        var s = t.data.status;
                        s !== e.TIM.TYPES.JOIN_STATUS_SUCCESS && s !== e.TIM.TYPES.JOIN_STATUS_ALREADY_IN_GROUP || (e.sendAvailable = !0)
                    })).catch((function () {
                    }))
                }, quitGroupLiveAvChatRoom: function () {
                    this.tim.quitGroup(this.groupLiveInfo.roomID).then((function () {
                    })).catch((function () {
                    }))
                }, sendMessage: function () {
                    var e = this;
                    if (this.sendAvailable) {
                        if ("" === this.messageContent || 0 === this.messageContent.trim().length) return this.messageContent = "", void this.$store.commit("showMessage", {
                            message: "不能发送空消息哦！",
                            type: "info"
                        });
                        var t = this.tim.createTextMessage({
                            to: this.groupLiveInfo.roomID,
                            conversationType: this.TIM.TYPES.CONV_GROUP,
                            payload: {text: this.messageContent}
                        });
                        this.$store.commit("pushAvChatRoomMessageList", JSON.parse(JSON.stringify(t))), this.tim.sendMessage(t).catch((function (t) {
                            e.$store.commit("showMessage", {type: "error", message: t.message})
                        })), this.messageContent = ""
                    } else this.$store.commit("showMessage", {message: "开始直播后可以互动聊天哦！", type: "warning"})
                }
            }, watch: {
                avChatRoomMessageList: function () {
                    var e = this;
                    this.$nextTick((function () {
                        var t = e.$refs["message-list"];
                        t && (t.scrollTop = t.scrollHeight)
                    }))
                }
            }
        }, za = Ha, Qa = (s("1376"), Object(G["a"])(za, La, Sa, !1, null, "2cc22d7d", null)), Ja = Qa.exports;

        function Ka(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Za(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Ka(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Ka(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Xa = {
                name: "groupLive", data: function () {
                    return {groupLiveVisible: !1, channel: 1}
                }, computed: Za({}, Object(H["c"])({
                    userID: function (e) {
                        return e.user.userID
                    }, groupID: function (e) {
                        return e.groupLive.groupLiveInfo.groupID
                    }, roomID: function (e) {
                        return e.groupLive.groupLiveInfo.roomID
                    }, anchorID: function (e) {
                        return e.groupLive.groupLiveInfo.anchorID
                    }
                })), mounted: function () {
                    var e = this;
                    this.$bus.$on("open-group-live", (function (t) {
                        e.channel = t.channel, e.groupLiveVisible = !0
                    })), this.$bus.$on("close-group-live", (function () {
                        e.groupLiveVisible = !1, e.$store.commit("clearAvChatRoomMessageList")
                    }))
                }, beforeDestroy: function () {
                    this.$bus.$off("open-group-live"), this.$bus.$off("close-group-live")
                }, components: {livePusher: Ca, livePlayer: ja, liveChat: Ja}, methods: {}
            }, Wa = Xa, ec = (s("3a6b"), Object(G["a"])(Wa, sa, ia, !1, null, "d0f30658", null)), tc = ec.exports,
            sc = function () {
                var e = this, t = e.$createElement, s = e._self._c || t;
                return s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.dialling || e.calling || e.isDialled,
                        expression: "dialling || calling || isDialled"
                    }], staticClass: "call-container"
                }, [s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.isDialled,
                        expression: "isDialled"
                    }], staticClass: "choose"
                }, [s("div", {staticClass: "title"}, [e._v("\n                " + e._s(e.sponsor) + "来通话啦\n            ")]), s("div", {staticClass: "buttons"}, [s("div", {
                    staticClass: "accept",
                    on: {
                        click: function (t) {
                            return e.handleDebounce(e.accept, 500)
                        }
                    }
                }), s("div", {
                    staticClass: "refuse", on: {
                        click: function (t) {
                            return e.handleDebounce(e.refuse, 500)
                        }
                    }
                })])]), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.dialling || e.calling,
                        expression: "dialling || calling"
                    }], staticClass: "call"
                }, [e.dialling && "C2C" === e.currentConversationType ? s("div", {staticClass: "title"}, [e._v("\n                正在呼叫" + e._s(e.toAccount) + "...\n            ")]) : e._e(), e.dialling && "GROUP" === e.currentConversationType ? s("div", {staticClass: "title"}, [e._v("\n                正在呼叫...\n            ")]) : e._e(), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.callType === e.TRTCCalling.CALL_TYPE.VIDEO_CALL && e.calling,
                        expression: " callType === TRTCCalling.CALL_TYPE.VIDEO_CALL && calling"
                    }]
                }, [s("div", {
                    staticClass: "small-group",
                    attrs: {id: "small-group"}
                }, [s("div", {staticClass: "video-box", attrs: {id: "local"}}), e._l(e.callingUserList, (function (e) {
                    return s("div", {key: "video-" + e, staticClass: "video-box", attrs: {id: "video-" + e}})
                }))], 2)]), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.callType === e.TRTCCalling.CALL_TYPE.AUDIO_CALL && e.calling,
                        expression: "callType === TRTCCalling.CALL_TYPE.AUDIO_CALL && calling"
                    }], staticClass: "audio-box"
                }, [s("div", {
                    directives: [{name: "show", rawName: "v-show", value: e.calling, expression: "calling"}],
                    staticClass: "aduio-call"
                }, [s("img", {
                    staticClass: "audio-img",
                    attrs: {src: e.myAvatar}
                }), s("p", {staticStyle: {"text-align": "center"}}, [s("span", {staticClass: "nick-text"}, [e._v(e._s(e.myNick))]), e.isMicOn ? s("i", {
                    staticClass: "el-icon-microphone micr-icon",
                    staticStyle: {color: "#006FFF"}
                }) : s("i", {staticClass: "el-icon-turn-off-microphone micr-icon"})])]), e._l(e.invitedUserInfo, (function (t) {
                    return s("div", {
                        key: "video-" + t.userID,
                        staticClass: "aduio-call"
                    }, [s("img", {
                        staticClass: "audio-img",
                        attrs: {src: t.avatar || e.defaultAvatar}
                    }), s("p", {staticStyle: {"text-align": "center"}}, [s("span", {staticClass: "nick-text"}, [e._v(e._s(t.nick || t.userID))]), !0 === t.isInvitedMicOn || void 0 == t.isInvitedMicOn ? s("i", {
                        staticClass: "el-icon-microphone micr-icon",
                        staticStyle: {color: "#006FFF"}
                    }) : s("i", {staticClass: "el-icon-turn-off-microphone micr-icon"})])])
                }))], 2), s("div", {
                    directives: [{
                        name: "show",
                        rawName: "v-show",
                        value: e.calling,
                        expression: "calling"
                    }], staticClass: "duration"
                }, [e._v("\n                " + e._s(e.formatDurationStr) + "\n            ")]), e.callType === e.TRTCCalling.CALL_TYPE.VIDEO_CALL ? s("div", {staticClass: "buttons"}, [s("div", {
                    class: e.isCamOn ? "videoOn" : "videoOff",
                    on: {click: e.videoHandler}
                }), s("div", {
                    staticClass: "refuse", on: {
                        click: function (t) {
                            return e.handleDebounce(e.leave, 500)
                        }
                    }
                }), s("div", {
                    class: e.isMicOn ? "micOn" : "micOff",
                    on: {click: e.micHandler}
                })]) : s("div", {staticClass: "buttons"}, [s("div", {
                    staticClass: "refuse", on: {
                        click: function (t) {
                            return e.handleDebounce(e.leave, 500)
                        }
                    }
                }), s("div", {class: e.isMicOn ? "micOn" : "micOff", on: {click: e.micHandler}})])])])
            }, ic = [];

        function nc(e) {
            return e < 10 ? "0".concat(e) : e
        }

        function rc(e) {
            if (e < 60) return "00:00:".concat(nc(e));
            if (e < 3600) {
                var t = parseInt(e / 60), s = e - 60 * t;
                return "00:".concat(nc(t), ":").concat(nc(s))
            }
            var i = parseInt(e / 3600), n = e - 3600 * i, r = parseInt(n / 60), o = n - 60 * r;
            return "".concat(nc(i), ":").concat(nc(r), ":").concat(nc(o))
        }

        function oc(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function ac(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? oc(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : oc(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var cc = {
            name: "CallLayer",
            data: function () {
                return {
                    timeout: null,
                    callType: 1,
                    Trtc: void 0,
                    isCamOn: !0,
                    isMicOn: !0,
                    isInvitedMicOn: !0,
                    maskShow: !1,
                    isLocalMain: !0,
                    start: 0,
                    end: 0,
                    duration: 0,
                    hangUpTimer: 0,
                    ready: !1,
                    dialling: !1,
                    calling: !1,
                    isDialled: !1,
                    inviteID: "",
                    inviteData: {},
                    sponsor: "",
                    invitedUserID: [],
                    invitedNick: "",
                    invitedUserInfo: [],
                    defaultAvatar: "https://imgcache.qq.com/open/qcloud/video/act/webim-avatar/avatar-3.png",
                    viewLocalDomID: "",
                    callingUserList: [],
                    callingType: "C2C",
                    isStartLocalView: !1,
                    callingTips: {callEnd: 1, callTimeout: 5}
                }
            },
            computed: ac(ac(ac({}, Object(H["b"])(["toAccount", "currentConversationType"])), Object(H["c"])({
                currentConversation: function (e) {
                    return e.conversation.currentConversation
                }, currentUserProfile: function (e) {
                    return e.user.currentUserProfile
                }, callingInfo: function (e) {
                    return e.conversation.callingInfo
                }, userID: function (e) {
                    return e.user.userID
                }, userSig: function (e) {
                    return e.user.userSig
                }, videoRoom: function (e) {
                    return e.video.videoRoom
                }, sdkAppID: function (e) {
                    return e.user.sdkAppID
                }
            })), {}, {
                formatDurationStr: function () {
                    return rc(this.duration)
                }, myAvatar: function () {
                    return this.currentUserProfile.avatar || this.defaultAvatar
                }, myNick: function () {
                    return this.currentUserProfile.nick || this.userID
                }
            }),
            created: function () {
                this.initListener()
            },
            watch: {
                callingUserList: {
                    handler: function (e) {
                        var t = this;
                        if (e.length < 2 && this.calling) this.$nextTick((function () {
                            var s = "video-".concat(e[0]), i = window.document.getElementById(s),
                                n = window.document.getElementById("local"),
                                r = window.document.getElementById("small-group");
                            i && i.classList && n && n.classList && (i && i.classList.remove("video-box"), n && n.classList.remove("video-box"), r && r.classList.add("small-group_box"), t.isLocalMain ? i.classList.add("big") : i.classList.add("small"), t.isLocalMain ? n.classList.add("small") : i.classList.add("big"))
                        })); else if (e.length >= 2 && this.calling) {
                            var s = window.document.getElementById("small-group");
                            s && s.classList.remove("small-group_box");
                            var i = window.document.getElementById("small-group").childNodes;
                            i.forEach((function (e, t) {
                                0 === t && e.classList.remove("small"), e.classList.remove("big"), e.classList.add("video-box")
                            }))
                        }
                    }, deep: !0, immediate: !0
                }
            },
            destroyed: function () {
                var e = this;
                this.removeListener(), window.addEventListener("beforeunload", (function () {
                    e.videoCallLogOut()
                })), window.addEventListener("leave", (function () {
                    e.videoCallLogOut()
                }))
            },
            mounted: function () {
                this.$bus.$on("video-call", this.videoCalling), this.$bus.$on("audio-call", this.audioCalling)
            },
            beforeDestroy: function () {
                this.$bus.$off("video-call", this.videoCalling), this.$bus.$off("audio-call", this.audioCalling)
            },
            methods: {
                videoCallLogOut: function () {
                    (this.dialling || this.calling) && this.leave(), this.isDialled && this.refuse()
                }, initListener: function () {
                    this.trtcCalling.on(this.TRTCCalling.EVENT.ERROR, this.handleError), this.trtcCalling.on(this.TRTCCalling.EVENT.INVITED, this.handleNewInvitationReceived), this.trtcCalling.on(this.TRTCCalling.EVENT.USER_ENTER, this.handleUserEnter), this.trtcCalling.on(this.TRTCCalling.EVENT.USER_LEAVE, this.handleUserLeave), this.trtcCalling.on(this.TRTCCalling.EVENT.REJECT, this.handleInviteeReject), this.trtcCalling.on(this.TRTCCalling.EVENT.LINE_BUSY, this.handleInviteeLineBusy), this.trtcCalling.on(this.TRTCCalling.EVENT.CALLING_CANCEL, this.handleInviterCancel), this.trtcCalling.on(this.TRTCCalling.EVENT.KICKED_OUT, this.handleKickedOut), this.trtcCalling.on(this.TRTCCalling.EVENT.CALLING_TIMEOUT, this.handleCallTimeout), this.trtcCalling.on(this.TRTCCalling.EVENT.NO_RESP, this.handleNoResponse), this.trtcCalling.on(this.TRTCCalling.EVENT.CALLING_END, this.handleCallEnd), this.trtcCalling.on(this.TRTCCalling.EVENT.USER_VIDEO_AVAILABLE, this.handleUserVideoChange), this.trtcCalling.on(this.TRTCCalling.EVENT.USER_AUDIO_AVAILABLE, this.handleUserAudioChange)
                }, removeListener: function () {
                    this.trtcCalling.off(this.TRTCCalling.EVENT.ERROR, this.handleError), this.trtcCalling.off(this.TRTCCalling.EVENT.INVITED, this.handleNewInvitationReceived), this.trtcCalling.off(this.TRTCCalling.EVENT.USER_ENTER, this.handleUserEnter), this.trtcCalling.off(this.TRTCCalling.EVENT.USER_LEAVE, this.handleUserLeave), this.trtcCalling.off(this.TRTCCalling.EVENT.REJECT, this.handleInviteeReject), this.trtcCalling.off(this.TRTCCalling.EVENT.LINE_BUSY, this.handleInviteeLineBusy), this.trtcCalling.off(this.TRTCCalling.EVENT.CALLING_CANCEL, this.handleInviterCancel), this.trtcCalling.off(this.TRTCCalling.EVENT.KICKED_OUT, this.handleKickedOut), this.trtcCalling.off(this.TRTCCalling.EVENT.CALLING_TIMEOUT, this.handleCallTimeout), this.trtcCalling.off(this.TRTCCalling.EVENT.NO_RESP, this.handleNoResponse), this.trtcCalling.off(this.TRTCCalling.EVENT.CALLING_END, this.handleCallEnd), this.trtcCalling.off(this.TRTCCalling.EVENT.USER_VIDEO_AVAILABLE, this.handleUserVideoChange), this.trtcCalling.off(this.TRTCCalling.EVENT.USER_AUDIO_AVAILABLE, this.handleUserAudioChange)
                }, handleError: function () {
                }, handleNewInvitationReceived: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e(t) {
                        var s, i, n, r, o;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    s = t.inviteID, i = t.sponsor, n = t.inviteData, r = t.userIDList, o = t.isGroupCall, this.inviteID = s, this.inviteData = n, this.callType = n.callType, this.sponsor = i, this.invitedUserID = JSON.parse(JSON.stringify(r)), this.callingInfo.type = o ? this.TIM.TYPES.CONV_GROUP : this.TIM.TYPES.CONV_C2C, this.changeState("isDialled", !0);
                                case 8:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t(t) {
                        return e.apply(this, arguments)
                    }

                    return t
                }(), accept: function () {
                    var e = this;
                    this.trtcCalling.accept({
                        inviteID: this.inviteID,
                        roomID: this.inviteData.roomID,
                        callType: this.inviteData.callType
                    }).then((function (t) {
                        e.changeState("calling", !0), t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message)
                    }))
                }, reject: function () {
                    var e = this, t = this.inviteData.callType;
                    this.trtcCalling.reject({inviteID: this.inviteID, isBusy: !1, callType: t}).then((function (t) {
                        t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message)
                    }))
                }, handleDebounce: function (e, t) {
                    var s = this, i = arguments;
                    this.timeout && clearTimeout(this.timeout), this.timeout = setTimeout((function () {
                        e.apply(s, i)
                    }), t)
                }, handleUserEnter: function (e) {
                    var t = this, s = e.userID;
                    this.changeState("dialling", !0), this.isAccept(), this.callingUserList.length >= 2 && (this.callingType = this.TIM.TYPES.CONV_GROUP), -1 === this.callingUserList.indexOf(s) && (this.callType === this.TRTCCalling.CALL_TYPE.AUDIO_CALL ? this.getUserAvatar(s) : this.callingUserList.push(s)), this.callType === this.TRTCCalling.CALL_TYPE.VIDEO_CALL && this.$nextTick((function () {
                        t.isStartLocalView || t.startLocalView(), t.startRemoteView(s)
                    }))
                }, handleUserLeave: function (e) {
                    var t = e.userID;
                    if (this.callType !== this.TRTCCalling.CALL_TYPE.AUDIO_CALL) {
                        var s = this.callingUserList.findIndex((function (e) {
                            return e === t
                        }));
                        s >= 0 && this.callingUserList.splice(s, 1)
                    } else {
                        var i = this.invitedUserInfo.findIndex((function (e) {
                            return e.userID === t
                        }));
                        i >= 0 && this.invitedUserInfo.splice(i, 1)
                    }
                }, handleInviteeReject: function (e) {
                    var t = e.userID;
                    this.userID === this.sponsor && (this.setCallingstatus(t), this.$store.commit("showMessage", {message: "".concat(t, "拒绝通话")}))
                }, setCallingstatus: function (e) {
                    var t = this.invitedUserID.indexOf(e);
                    t >= 0 && this.invitedUserID.splice(t, 1), 0 === this.invitedUserID.length && (this.changeState("isDialled", !1), this.changeState("dialling", !1))
                }, handleInviteeLineBusy: function (e) {
                    var t = e.sponsor, s = e.userID;
                    t === this.userID && (this.setCallingstatus(s), this.$store.commit("showMessage", {message: "".concat(s, "忙线")}))
                }, handleInviterCancel: function () {
                    this.changeState("isDialled", !1), this.$store.commit("showMessage", {message: "通话已取消"})
                }, handleKickedOut: function () {
                }, handleCallTimeout: function (e) {
                    var t = this, s = e.userIDList;
                    this.calling || (this.userID !== this.sponsor ? (s.indexOf(this.userID) > -1 && this.toAccount && this.sendMessage(this.userID, "", this.callingTips.callTimeout), this.changeState("isDialled", !1)) : s.forEach((function (e) {
                        t.setCallingstatus(e)
                    })))
                }, handleCallEnd: function (e) {
                    var t = e.userID, s = e.callEnd;
                    (t === this.userID && 0 === this.invitedUserID.length || 0 === this.callingUserList) && this.sendMessage(t, s, this.callingTips.callEnd), this.changeState("dialling", !1), this.isMicOn = !0, this.isCamOn = !0, this.maskShow = !1, this.isStartLocalView = !1
                }, handleNoResponse: function (e) {
                    var t = this, s = e.sponsor, i = e.userIDList;
                    s === this.userID && (i.forEach((function (e) {
                        t.setCallingstatus(e)
                    })), -1 === i.indexOf(this.userID) && this.sendMessage(i, "", this.callingTips.callTimeout))
                }, handleUserVideoChange: function () {
                }, handleUserAudioChange: function (e) {
                    var t = this.invitedUserInfo.findIndex((function (t) {
                        return t.userID === e.userID
                    }));
                    t >= 0 && (this.invitedUserInfo[t].isInvitedMicOn = e.isAudioAvailable)
                }, startLocalView: function () {
                    var e = this;
                    this.trtcCalling.startLocalView({userID: this.userID, videoViewDomID: "local"}).then((function () {
                        e.isStartLocalView = !0
                    }))
                }, stopLocalView: function () {
                    this.trtcCalling.stopLocalView({userID: this.userID, videoViewDomID: this.viewLocalDomID})
                }, startRemoteView: function (e) {
                    this.trtcCalling.startRemoteView({
                        userID: e,
                        videoViewDomID: "video-".concat(e)
                    }).then((function () {
                    }))
                }, stopRemoteView: function (e) {
                    this.trtcCalling.stopRemoteView({userID: this.userID, videoViewDomID: "video-".concat(e)})
                }, getUserAvatar: function (e) {
                    var t = this, s = this.invitedUserInfo.findIndex((function (t) {
                        return t.userID === e
                    }));
                    if (!(s >= 0)) {
                        var i = [e], n = this.tim.getUserProfile({userIDList: i});
                        n.then((function (e) {
                            e.data[0] && t.invitedUserInfo.push(e.data[0])
                        })).catch((function () {
                        }))
                    }
                }, changeState: function (e, t) {
                    var s = this, i = ["dialling", "isDialled", "calling"];
                    i.forEach((function (i) {
                        s[i] = i === e && t
                    })), this.$store.commit("UPDATE_ISBUSY", i.some((function (e) {
                        return s[e]
                    })))
                }, videoCalling: function () {
                    var e = this;
                    this.invitedUserID = JSON.parse(JSON.stringify(this.callingInfo.memberList)), this.sponsor = this.userID, this.calling || (this.callType = this.TRTCCalling.CALL_TYPE.VIDEO_CALL, this.isLocalMain = !0, this.callingInfo.type === this.TIM.TYPES.CONV_C2C ? this.trtcCalling.call({
                        userID: this.callingInfo.memberList[0],
                        type: this.callType,
                        timeout: 30
                    }).then((function (t) {
                        t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message), e.changeState("dialling", !0)
                    })) : this.trtcCalling.groupCall({
                        userIDList: this.callingInfo.memberList,
                        type: this.callType,
                        groupID: this.currentConversation.groupProfile.groupID,
                        timeout: 30
                    }).then((function (t) {
                        t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message), e.changeState("dialling", !0)
                    })))
                }, audioCalling: function () {
                    var e = this;
                    this.invitedUserID = this.callingInfo.memberList, this.sponsor = this.userID, this.calling || (this.callType = this.TRTCCalling.CALL_TYPE.AUDIO_CALL, "C2C" === this.callingInfo.type ? this.trtcCalling.call({
                        userID: this.callingInfo.memberList[0],
                        type: this.callType,
                        timeout: 30
                    }).then((function (t) {
                        e.changeState("dialling", !0), t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message)
                    })) : this.trtcCalling.groupCall({
                        userIDList: this.callingInfo.memberList,
                        type: this.callType,
                        groupID: this.currentConversation.groupProfile.groupID,
                        timeout: 30
                    }).then((function (t) {
                        e.changeState("dialling", !0), t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message)
                    })))
                }, leave: function () {
                    var e = this;
                    this.isMicOn = !0, this.isCamOn = !0, this.maskShow = !1, this.isStartLocalView = !1, this.calling ? this.hangUp() : this.trtcCalling.hangup().then((function (t) {
                        t.data.message.nick = e.currentUserProfile.nick, e.$store.commit("pushCurrentMessageList", t.data.message), e.changeState("dialling", !1), clearTimeout(e.timer)
                    }))
                }, refuse: function () {
                    this.changeState("isDialled", !1), this.reject()
                }, resetDuration: function (e) {
                    var t = this;
                    this.duration = e, this.hangUpTimer = setTimeout((function () {
                        var e = new Date;
                        t.resetDuration(parseInt((e - t.start) / 1e3))
                    }), 1e3)
                }, isAccept: function () {
                    clearTimeout(this.timer), this.changeState("calling", !0), clearTimeout(this.hangUpTimer), this.resetDuration(0), this.start = new Date
                }, hangUp: function () {
                    this.changeState("calling", !1), this.trtcCalling.hangup(), this.$store.commit("showMessage", {message: "已挂断"})
                }, videoHandler: function () {
                    this.isCamOn ? (this.isCamOn = !1, this.maskShow = !0, this.trtcCalling.closeCamera()) : (this.isCamOn = !0, this.maskShow = !1, this.trtcCalling.openCamera())
                }, micHandler: function () {
                    this.isMicOn ? (this.trtcCalling.setMicMute(!0), this.isMicOn = !1) : (this.trtcCalling.setMicMute(!1), this.isMicOn = !0)
                }, changeMainVideo: function () {
                    this.calling && (this.isLocalMain = !this.isLocalMain)
                }, sendMessage: function () {
                    var e = Object(ys["a"])(regeneratorRuntime.mark((function e(t, s, i) {
                        var n, r, o, a, c;
                        return regeneratorRuntime.wrap((function (e) {
                            while (1) switch (e.prev = e.next) {
                                case 0:
                                    return n = "", t = Array.isArray(t) ? t.join(",") : t, r = {
                                        to: this.toAccount,
                                        from: t,
                                        conversationType: this.currentConversationType,
                                        payload: {data: "", description: "", extension: ""}
                                    }, e.next = 5, this.tim.createCustomMessage(r);
                                case 5:
                                    o = e.sent, e.t0 = i, e.next = e.t0 === this.callingTips.callEnd ? 9 : e.t0 === this.callingTips.callTimeout ? 16 : 18;
                                    break;
                                case 9:
                                    if (this.currentConversationType !== this.TIM.TYPES.CONV_GROUP) {
                                        e.next = 14;
                                        break
                                    }
                                    return n = "结束群聊", e.abrupt("break", 20);
                                case 14:
                                    return n = 0 === s ? "取消通话" : "结束通话，通话时长：".concat(rc(s || this.duration)), e.abrupt("break", 20);
                                case 16:
                                    return n = "无应答", e.abrupt("break", 20);
                                case 18:
                                    return n = "", e.abrupt("break", 20);
                                case 20:
                                    this.currentConversationType === this.TIM.TYPES.CONV_GROUP && (o.groupID = this.toAccount, a = {
                                        operationType: 256,
                                        text: n,
                                        userIDList: []
                                    }, o.payload = a), this.currentConversationType === this.TIM.TYPES.CONV_C2C && (c = {text: n}, o.payload = c), this.currentConversationType === this.TIM.TYPES.CONV_GROUP && (o.type = this.TIM.TYPES.MSG_GRP_TIP), o.callType = "callingTips", this.$store.commit("pushCurrentMessageList", o);
                                case 25:
                                case"end":
                                    return e.stop()
                            }
                        }), e, this)
                    })));

                    function t(t, s, i) {
                        return e.apply(this, arguments)
                    }

                    return t
                }()
            }
        }, lc = cc, uc = (s("3801"), Object(G["a"])(lc, sc, ic, !1, null, "9b852512", null)), mc = uc.exports, pc = {
            VIDEO_AUDIO_CALL: 1,
            VIDEO_CALL_ACTION_DIALING: 0,
            VIDEO_CALL_ACTION_SPONSOR_CANCEL: 2,
            VIDEO_CALL_ACTION_REJECT: 4,
            VIDEO_CALL_ACTION_SPONSOR_TIMEOUT: 5,
            VIDEO_CALL_ACTION_ACCEPTED: 3,
            VIDEO_CALL_ACTION_HANGUP: 5
        };

        function hc(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function fc(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? hc(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : hc(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var dc = {
                title: "TIMSDK DEMO",
                data: function () {
                    return {loginType: 2}
                },
                components: {
                    Login: Go,
                    SideBar: _o,
                    CurrentConversation: Hn,
                    ImagePreviewer: Ho,
                    QrCodeList: ta,
                    GroupLive: tc,
                    Calling: mc
                },
                computed: fc(fc({}, Object(H["c"])({
                    currentUserProfile: function (e) {
                        return e.user.currentUserProfile
                    }, currentConversation: function (e) {
                        return e.conversation.currentConversation
                    }, videoCall: function (e) {
                        return e.conversation.videoCall
                    }, audioCall: function (e) {
                        return e.conversation.audioCall
                    }, isLogin: function (e) {
                        return e.user.isLogin
                    }, isSDKReady: function (e) {
                        return e.user.isSDKReady
                    }, isBusy: function (e) {
                        return e.video.isBusy
                    }, userID: function (e) {
                        return e.user.userID
                    }, userSig: function (e) {
                        return e.user.userSig
                    }, sdkAppID: function (e) {
                        return e.user.sdkAppID
                    }
                })), {}, {
                    showLoading: function () {
                        return !this.isSDKReady
                    }
                }),
                mounted: function () {
                    this.initListener()
                },
                watch: {
                    isLogin: function (e) {
                        e && Zo.clickStat("link_two", {show: "true"})
                    }
                },
                methods: {
                    initListener: function () {
                        this.tim.on(this.TIM.EVENT.SDK_READY, this.onReadyStateUpdate, this), this.tim.on(this.TIM.EVENT.SDK_NOT_READY, this.onReadyStateUpdate, this), this.tim.on(this.TIM.EVENT.KICKED_OUT, this.onKickOut), this.tim.on(this.TIM.EVENT.ERROR, this.onError), this.tim.on(this.TIM.EVENT.MESSAGE_RECEIVED, this.onReceiveMessage), this.tim.on(this.TIM.EVENT.CONVERSATION_LIST_UPDATED, this.onUpdateConversationList), this.tim.on(this.TIM.EVENT.GROUP_LIST_UPDATED, this.onUpdateGroupList), this.tim.on(this.TIM.EVENT.NET_STATE_CHANGE, this.onNetStateChange), this.tim.on(this.TIM.EVENT.MESSAGE_READ_BY_PEER, this.onMessageReadByPeer)
                    }, onReceiveMessage: function (e) {
                        var t = e.data;
                        this.handleVideoMessage(t), this.handleQuitGroupTip(t), this.handleCloseGroupLive(t), this.$store.commit("pushCurrentMessageList", t), this.$store.commit("pushAvChatRoomMessageList", t)
                    }, onError: function (e) {
                        var t = e.data;
                        "Network Error" !== t.message && this.$store.commit("showMessage", {
                            message: t.message,
                            type: "error"
                        })
                    }, onMessageReadByPeer: function () {
                    }, onReadyStateUpdate: function (e) {
                        var t = this, s = e.name, i = s === this.TIM.EVENT.SDK_READY;
                        this.$store.commit("toggleIsSDKReady", i), i && (this.tim.getMyProfile().then((function (e) {
                            var s = e.data;
                            t.$store.commit("updateCurrentUserProfile", s)
                        })).catch((function (e) {
                            t.$store.commit("showMessage", {type: "error", message: e.message})
                        })), this.$store.dispatch("getBlacklist"), this.trtcCalling.login({
                            sdkAppID: this.sdkAppID,
                            userID: this.userID,
                            userSig: this.userSig
                        }))
                    }, kickedOutReason: function (e) {
                        switch (e) {
                            case this.TIM.TYPES.KICKED_OUT_MULT_ACCOUNT:
                                return "由于多实例登录";
                            case this.TIM.TYPES.KICKED_OUT_MULT_DEVICE:
                                return "由于多设备登录";
                            case this.TIM.TYPES.KICKED_OUT_USERSIG_EXPIRED:
                                return "由于 userSig 过期";
                            default:
                                return ""
                        }
                    }, checkoutNetState: function (e) {
                        switch (e) {
                            case this.TIM.TYPES.NET_STATE_CONNECTED:
                                return {message: "已接入网络", type: "success"};
                            case this.TIM.TYPES.NET_STATE_CONNECTING:
                                return {message: "当前网络不稳定", type: "warning"};
                            case this.TIM.TYPES.NET_STATE_DISCONNECTED:
                                return {message: "当前网络不可用", type: "error"};
                            default:
                                return ""
                        }
                    }, onNetStateChange: function (e) {
                        this.$store.commit("showMessage", this.checkoutNetState(e.data.state))
                    }, onKickOut: function (e) {
                        this.$store.commit("showMessage", {
                            message: "".concat(this.kickedOutReason(e.data.type), "被踢出，请重新登录。"),
                            type: "error"
                        }), this.$store.commit("toggleIsLogin", !1), this.$store.commit("reset")
                    }, onUpdateConversationList: function (e) {
                        this.$store.commit("updateConversationList", e.data)
                    }, onUpdateGroupList: function (e) {
                        this.$store.commit("updateGroupList", e.data)
                    }, onReceiveGroupSystemNotice: function (e) {
                        var t = this, s = 4 === e.data.type,
                            i = "GROUP".concat(e.data.message.payload.groupProfile.groupID) === this.currentConversation.conversationID;
                        s && i && this.$store.commit("resetCurrentConversation"), F()({
                            title: "新系统通知",
                            message: us(e.data.message),
                            duration: 3e3,
                            onClick: function () {
                                var e = "@TIM#SYSTEM";
                                t.$store.dispatch("checkoutConversation", e)
                            }
                        })
                    }, selectConversation: function (e) {
                        e !== this.currentConversation.conversationID && this.$store.dispatch("checkoutConversation", e)
                    }, isJsonStr: function (e) {
                        try {
                            return JSON.parse(e), !0
                        } catch (t) {
                            return !1
                        }
                    }, handleVideoMessage: function (e) {
                        var t = this, s = e.filter((function (e) {
                            return e.type === t.TIM.TYPES.MSG_CUSTOM && t.isJsonStr(e.payload.data)
                        }));
                        if (0 !== s.length) {
                            var i = JSON.parse(s[0].payload.data);
                            if (i.action === pc.VIDEO_CALL_ACTION_DIALING) {
                                if (this.isBusy) return void this.$bus.$emit("busy", i, s[0]);
                                this.$store.commit("GENERATE_VIDEO_ROOM", i.room_id), this.selectConversation(s[0].conversationID), s[0].from !== this.userID && this.$bus.$emit("isCalled")
                            }
                            i.action === pc.VIDEO_CALL_ACTION_SPONSOR_CANCEL && this.$bus.$emit("missCall"), i.action === pc.VIDEO_CALL_ACTION_REJECT && this.$bus.$emit("isRefused"), i.action === pc.VIDEO_CALL_ACTION_SPONSOR_TIMEOUT && this.$bus.$emit("missCall"), i.action === pc.VIDEO_CALL_ACTION_ACCEPTED && this.$bus.$emit("isAccept"), i.action === pc.VIDEO_CALL_ACTION_HANGUP && this.$bus.$emit("isHungUp"), i.action === pc.VIDEO_CALL_ACTION_LINE_BUSY && this.$bus.$emit("isRefused"), i.action === pc.VIDEO_CALL_ACTION_ERROR && this.$bus.$emit("isRefused")
                        }
                    }, notifyMe: function (e) {
                        var t = this;
                        "Notification" in window && ("granted" === window.Notification.permission ? this.handleNotify(e) : "denied" !== window.Notification.permission && window.Notification.requestPermission().then((function (s) {
                            "granted" === s && t.handleNotify(e)
                        })))
                    }, handleNotify: function (e) {
                        var t = this, s = new window.Notification("有人提到了你", {
                            icon: "https://webim-1252463788.file.myqcloud.com/demo/img/logo.dc3be0d4.png",
                            body: e.payload.text
                        });
                        s.onclick = function () {
                            window.focus(), t.$store.dispatch("checkoutConversation", e.conversationID), s.close()
                        }
                    }, handleLinkClick: function () {
                        Zo.clickStat("link_two", {click: "true"})
                    }, handleQuitGroupTip: function (e) {
                        var t = this, s = e.filter((function (e) {
                            return t.currentConversation.conversationID === e.conversationID && e.type === t.TIM.TYPES.MSG_GRP_TIP && (e.payload.operationType === t.TIM.TYPES.GRP_TIP_MBR_QUIT || e.payload.operationType === t.TIM.TYPES.GRP_TIP_MBR_KICKED_OUT)
                        }));
                        s.length > 0 && s.forEach((function (e) {
                            (Array.isArray(e.payload.userIDList) || e.payload.userIDList.length > 0) && t.$store.commit("deleteGroupMemberList", e.payload.userIDList)
                        }))
                    }, handleCloseGroupLive: function (e) {
                        var t = this;
                        e.forEach((function (e) {
                            if (t.currentConversation.conversationID === e.conversationID && e.type === t.TIM.TYPES.MSG_CUSTOM) {
                                var s = {};
                                try {
                                    s = JSON.parse(e.payload.data)
                                } catch (i) {
                                    s = {}
                                }
                                s.roomId && 0 === Number(s.roomStatus) && t.$bus.$emit("close-group-live")
                            }
                        }))
                    }
                }
            }, gc = dc, vc = (s("2482"), Object(G["a"])(gc, B, Y, !1, null, null, null)), bc = vc.exports,
            Cc = (s("0fb7"), s("f529")), yc = s.n(Cc), Oc = s("75fc"), wc = s("3581"), Ic = s.n(wc),
            Ac = ls.a.create({SDKAppID: window.genTestUserSig("").SDKAppID});
        window.setLogLevel = Ac.setLogLevel, Ac.setLogLevel(4), Ac.registerPlugin({"tim-upload-plugin": Ic.a});
        var Mc = Ac;
        s("386d");

        function Pc(e) {
            var t = e > 0;
            t ? document.title.search(/\((.*?)\)/) >= 0 ? document.title = document.title.replace(/\((.*?)\)/, "(".concat(e > 99 ? "99+" : e, ")")) : document.title = "(".concat(e, ")").concat(document.title) : document.title = document.title.replace(/\((.*?)\)/, "")
        }

        var Tc = {
            state: {
                currentConversation: {},
                currentMessageList: [],
                nextReqMessageID: "",
                isCompleted: !1,
                conversationList: [],
                callingInfo: {memberList: [], type: "C2C"},
                audioCall: !1,
                isShowConversationList: !1,
                selectedMessageList: [],
                relayType: 1,
                mergerMessageList: [],
                mergerMessage: {},
                relayMessage: {},
                selectMessage: !1
            }, getters: {
                toAccount: function (e) {
                    if (!e.currentConversation || !e.currentConversation.conversationID) return "";
                    switch (e.currentConversation.type) {
                        case"C2C":
                            return e.currentConversation.conversationID.replace("C2C", "");
                        case"GROUP":
                            return e.currentConversation.conversationID.replace("GROUP", "");
                        default:
                            return e.currentConversation.conversationID
                    }
                }, currentConversationType: function (e) {
                    return e.currentConversation && e.currentConversation.type ? e.currentConversation.type : ""
                }, totalUnreadCount: function (e) {
                    var t = e.conversationList.reduce((function (t, s) {
                        return $c.getters.hidden || e.currentConversation.conversationID !== s.conversationID ? t + s.unreadCount : t
                    }), 0);
                    return Pc(t), t
                }, imgUrlList: function (e) {
                    return e.currentMessageList.filter((function (e) {
                        return e.type === ls.a.TYPES.MSG_IMAGE && !e.isRevoked
                    })).map((function (e) {
                        return e.payload.imageInfoArray[0].url
                    }))
                }
            }, mutations: {
                setCallingList: function (e, t) {
                    e.callingInfo.memberList = t.memberList, e.callingInfo.type = t.type
                }, showConversationList: function (e, t) {
                    e.isShowConversationList = t
                }, setSelectedMessageList: function (e, t) {
                    e.selectedMessageList = t
                }, setRelayType: function (e, t) {
                    e.relayType = t
                }, setMergerMessage: function (e, t) {
                    e.mergerMessage = t, e.mergerMessageList = [].concat(Object(Oc["a"])(e.mergerMessageList), [t])
                }, setRelayMessage: function (e, t) {
                    e.relayMessage = t
                }, updateMergerMessage: function (e, t) {
                    e.mergerMessage = t, e.mergerMessageList.pop()
                }, setSelectedMessage: function (e, t) {
                    e.selectMessage = t
                }, resetSelectedMessage: function (e, t) {
                    e.selectMessage = t, Object.assign(e, {selectedMessageList: []})
                }, resetMergerMessage: function (e, t) {
                    e.mergerMessagePop = t, Object.assign(e, {mergerMessage: {}, mergerMessageList: []})
                }, showAudioCall: function (e, t) {
                    e.audioCall = t
                }, updateCurrentConversation: function (e, t) {
                    e.currentConversation = t, e.currentMessageList = [], e.nextReqMessageID = "", e.isCompleted = !1
                }, updateConversationList: function (e, t) {
                    e.conversationList = t
                }, resetCurrentConversation: function (e) {
                    e.currentConversation = {}
                }, pushCurrentMessageList: function (e, t) {
                    if (e.currentConversation.conversationID) if (Array.isArray(t)) {
                        var s = t.filter((function (t) {
                            return t.conversationID === e.currentConversation.conversationID
                        }));
                        e.currentMessageList = [].concat(Object(Oc["a"])(e.currentMessageList), Object(Oc["a"])(s)), ms(e.currentMessageList)
                    } else t.conversationID === e.currentConversation.conversationID && (e.currentMessageList = [].concat(Object(Oc["a"])(e.currentMessageList), [t]), ms(e.currentMessageList))
                }, removeMessage: function (e, t) {
                    var s = e.currentMessageList.findIndex((function (e) {
                        var s = e.ID;
                        return s === t.ID
                    }));
                    s >= 0 && e.currentMessageList.splice(s, 1)
                }, reset: function (e) {
                    Object.assign(e, {
                        currentConversation: {},
                        currentMessageList: [],
                        nextReqMessageID: "",
                        isCompleted: !1,
                        conversationList: []
                    })
                }
            }, actions: {
                getMessageList: function (e, t) {
                    if (e.state.isCompleted) e.commit("showMessage", {message: "已经没有更多的历史消息了哦", type: "info"}); else {
                        var s = e.state, i = s.nextReqMessageID, n = s.currentMessageList;
                        Mc.getMessageList({conversationID: t, nextReqMessageID: i, count: 15}).then((function (t) {
                            e.state.nextReqMessageID = t.data.nextReqMessageID, e.state.isCompleted = t.data.isCompleted, e.state.currentMessageList = [].concat(Object(Oc["a"])(t.data.messageList), Object(Oc["a"])(n)), ms(e.state.currentMessageList)
                        }))
                    }
                }, checkoutConversation: function (e, t) {
                    if (e.commit("resetCurrentMemberList"), e.commit("resetSelectedMessage", !1), e.state.currentConversation.conversationID) {
                        var s = e.state.currentConversation.conversationID;
                        Mc.setMessageRead({conversationID: s})
                    }
                    return Mc.setMessageRead({conversationID: t}), Mc.getConversationProfile(t).then((function (s) {
                        var i = s.data;
                        return e.commit("updateCurrentConversation", i.conversation), e.dispatch("getMessageList", t), i.conversation.type === ls.a.TYPES.CONV_GROUP ? e.dispatch("getGroupMemberList", i.conversation.groupProfile.groupID) : Promise.resolve()
                    }))
                }
            }
        }, Ec = Tc, Dc = {
            state: {groupList: [], currentMemberList: [], createGroupModelVisible: !1},
            getters: {
                hasGroupList: function (e) {
                    return e.groupList.length > 0
                }
            },
            mutations: {
                updateGroupList: function (e, t) {
                    e.groupList = t
                }, updateCreateGroupModelVisible: function (e, t) {
                    e.createGroupModelVisible = t
                }, updateCurrentMemberList: function (e, t) {
                    e.currentMemberList = [].concat(Object(Oc["a"])(e.currentMemberList), Object(Oc["a"])(t))
                }, deleteGroupMemeber: function (e, t) {
                    e.currentMemberList = e.currentMemberList.filter((function (e) {
                        return e.userID !== t
                    }))
                }, deleteGroupMemberList: function (e, t) {
                    e.currentMemberList = e.currentMemberList.filter((function (e) {
                        return !t.includes(e.userID)
                    }))
                }, resetCurrentMemberList: function (e) {
                    e.currentMemberList = []
                }, reset: function (e) {
                    Object.assign(e, {groupList: [], currentMemberList: [], createGroupModelVisible: !1})
                }
            },
            actions: {
                updateGroupList: function (e, t) {
                    e.commit("updateGroupList", t)
                }, getGroupMemberList: function (e, t) {
                    return Mc.getGroupMemberList({
                        groupID: t,
                        offset: e.state.currentMemberList.length,
                        count: 30
                    }).then((function (t) {
                        return e.commit("updateCurrentMemberList", t.data.memberList), t
                    }))
                }
            }
        }, _c = Dc, jc = {
            state: {currentUserProfile: {}, isLogin: !1, isSDKReady: !1, userID: 0, userSig: "", sdkAppID: 0},
            mutations: {
                updateCurrentUserProfile: function (e, t) {
                    e.currentUserProfile = t
                }, toggleIsLogin: function (e, t) {
                    e.isLogin = "undefined" === typeof t ? !e.isLogin : t
                }, toggleIsSDKReady: function (e, t) {
                    e.isSDKReady = "undefined" === typeof t ? !e.isSDKReady : t
                }, reset: function (e) {
                    Object.assign(e, {currentUserProfile: {}, isLogin: !1, isSDKReady: !1})
                }, GET_USER_INFO: function (e, t) {
                    e.userID = t.userID, e.userSig = t.userSig, e.sdkAppID = t.sdkAppID
                }
            },
            actions: {
                logout: function (e) {
                    e.rootState.conversation.currentConversation.conversationID && Mc.setMessageRead({conversationID: e.rootState.conversation.currentConversation.conversationID}), Mc.logout().then((function () {
                        e.commit("toggleIsLogin"), e.commit("stopComputeCurrent"), e.commit("reset")
                    }))
                }
            }
        }, Lc = jc, Sc = {
            state: {videoRoom: 0, isBusy: !1}, mutations: {
                GENERATE_VIDEO_ROOM: function (e, t) {
                    e.videoRoom = t || Math.ceil(Math.random() * (Math.pow(2, 32) - 1))
                }, UPDATE_ISBUSY: function (e, t) {
                    e.isBusy = t
                }
            }
        }, kc = Sc, xc = {
            state: {friendList: [], createGroupModelVisible: !1}, mutations: {
                upadteFriendList: function (e, t) {
                    e.friendList = t
                }, reset: function (e) {
                    Object.assign(e, {friendList: [], createGroupModelVisible: !1})
                }
            }
        }, Rc = xc, Nc = {
            state: {blacklist: []}, mutations: {
                updateBlacklist: function (e, t) {
                    e.blacklist = t
                }, removeFromBlacklist: function (e, t) {
                    e.blacklist = e.blacklist.filter((function (e) {
                        return e.userID !== t
                    }))
                }, reset: function (e) {
                    Object.assign(e, {blacklist: []})
                }
            }, actions: {
                getBlacklist: function (e) {
                    Mc.getBlacklist().then((function (t) {
                        var s = t.data;
                        s.length > 0 && Mc.getUserProfile({userIDList: s}).then((function (t) {
                            var s = t.data;
                            e.commit("updateBlacklist", s)
                        }))
                    }))
                }
            }
        }, Gc = Nc;

        function Uc(e, t) {
            var s = Object.keys(e);
            if (Object.getOwnPropertySymbols) {
                var i = Object.getOwnPropertySymbols(e);
                t && (i = i.filter((function (t) {
                    return Object.getOwnPropertyDescriptor(e, t).enumerable
                }))), s.push.apply(s, i)
            }
            return s
        }

        function Vc(e) {
            for (var t = 1; t < arguments.length; t++) {
                var s = null != arguments[t] ? arguments[t] : {};
                t % 2 ? Uc(Object(s), !0).forEach((function (t) {
                    Object(q["a"])(e, t, s[t])
                })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(s)) : Uc(Object(s)).forEach((function (t) {
                    Object.defineProperty(e, t, Object.getOwnPropertyDescriptor(s, t))
                }))
            }
            return e
        }

        var Bc = {
            state: {
                groupLiveInfo: {groupID: 0, roomID: 0, anchorID: 0, roomName: "", isNeededQuitRoom: 0},
                avChatRoomMessageList: [],
                avChatRoomGiftMessageList: [],
                avChatRoomBarrageMessageList: []
            }, getters: {}, mutations: {
                updateGroupLiveInfo: function (e, t) {
                    e.groupLiveInfo = Vc(Vc({}, e.groupLiveInfo), t)
                }, resetGroupLiveInfo: function (e, t) {
                    e.groupLiveInfo = Vc(Vc({}, e.groupLiveInfo), t)
                }, clearAvChatRoomMessageList: function (e) {
                    e.avChatRoomMessageList = [], e.avChatRoomBarrageMessageList = [], e.avChatRoomGiftMessageList = []
                }, pushAvChatRoomMessageList: function (e, t) {
                    if (Array.isArray(t)) {
                        var s = t.filter((function (t) {
                            return t.to === e.groupLiveInfo.roomID
                        }));
                        e.avChatRoomMessageList = [].concat(Object(Oc["a"])(e.avChatRoomMessageList), Object(Oc["a"])(s))
                    } else t.to === e.groupLiveInfo.roomID && (e.avChatRoomMessageList = [].concat(Object(Oc["a"])(e.avChatRoomMessageList), [t]))
                }
            }, actions: {}
        }, Yc = Bc;
        j["default"].use(H["a"]);
        var $c = new H["a"].Store({
                state: {current: Date.now(), intervalID: 0, message: void 0},
                getters: {
                    hidden: function (e) {
                        e.current;
                        return "function" !== typeof document.hasFocus ? document.hidden : !document.hasFocus()
                    }
                },
                mutations: {
                    startComputeCurrent: function (e) {
                        e.intervalID = setInterval((function () {
                            e.current = Date.now()
                        }), 500)
                    }, stopComputeCurrent: function (e) {
                        clearInterval(e.intervalID), e.intervalID = 0
                    }, showMessage: function (e, t) {
                        e.message && e.message.close(), e.message = yc()({
                            message: t.message,
                            type: t.type || "success",
                            duration: t.duration || 2e3,
                            offset: 40
                        })
                    }
                },
                modules: {conversation: Ec, group: _c, friend: Rc, blacklist: Gc, user: Lc, video: kc, groupLive: Yc}
            }), Fc = s("f0ef"), qc = s.n(Fc), Hc = s("4eb5"), zc = s.n(Hc),
            Qc = (s("ed2c"), s("8e84"), s("ebd9"), s("fc9e")), Jc = s.n(Qc),
            Kc = {SDKAppID: window.genTestUserSig("").SDKAppID}, Zc = new Jc.a(Kc);
        Zc.setLogLevel(0);
        var Xc = Zc;
        window.tim = Mc, window.TIM = ls.a, window.TRTCCalling = Jc.a, window.trtcCalling = Xc, window.store = $c, j["default"].prototype.$bus = new j["default"], j["default"].prototype.tim = Mc, j["default"].prototype.TIM = ls.a, j["default"].prototype.TWebLive = qc.a, j["default"].prototype.$store = $c, j["default"].prototype.$confirm = _.a.confirm, j["default"].prototype.trtcCalling = Xc, j["default"].prototype.TRTCCalling = Jc.a, j["default"].use(E.a), j["default"].use(P.a), j["default"].use(A.a), j["default"].use(w.a), j["default"].use(y.a), j["default"].use(b.a), j["default"].use(g.a), j["default"].use(f.a), j["default"].use(p.a), j["default"].use(zc.a), j["default"].use(u.a), j["default"].use(c.a), j["default"].use(o.a), j["default"].use(n.a), j["default"].component("avatar", V), new j["default"]({
            render: function (e) {
                return e(bc)
            }
        }).$mount("#app")
    }, 5724: function (e, t, s) {
        "use strict";
        s("ba16")
    }, "5f59": function (e, t, s) {
    }, "60ed": function (e, t, s) {
        "use strict";
        s("0553")
    }, 6794: function (e, t, s) {
        "use strict";
        s("6d8f")
    }, "691c": function (e, t, s) {
        "use strict";
        s("5406")
    }, "6be7": function (e, t, s) {
    }, "6d8f": function (e, t, s) {
    }, "6e7b": function (e, t, s) {
        "use strict";
        s("d341")
    }, 7360: function (e, t, s) {
        e.exports = s.p + "img/merger-relay.2acba573.png"
    }, 7412: function (e, t, s) {
        e.exports = s.p + "img/pause-icon.4e4565e0.png"
    }, "742a": function (e, t, s) {
    }, "76e5": function (e, t, s) {
    }, "777c": function (e, t, s) {
        "use strict";
        s("c1f9")
    }, "7a62": function (e, t, s) {
    }, "7a76": function (e, t, s) {
        "use strict";
        s("6be7")
    }, "7a96": function (e, t, s) {
    }, "7b7d": function (e, t, s) {
        "use strict";
        s("8652")
    }, "7dce": function (e, t, s) {
    }, "7ef9": function (e, t, s) {
        "use strict";
        s("5f59")
    }, 8127: function (e, t, s) {
        "use strict";
        s("12d4")
    }, "826b": function (e, t, s) {
        "use strict";
        s("fb0d")
    }, "854e": function (e, t, s) {
    }, 8652: function (e, t, s) {
    }, 8656: function (e, t, s) {
        "use strict";
        s("406c")
    }, "86b5": function (e, t, s) {
        "use strict";
        s("b8dc")
    }, "873f": function (e, t, s) {
        "use strict";
        s("a396")
    }, 8831: function (e, t, s) {
    }, "8d76": function (e, t, s) {
    }, "8e84": function (e, t, s) {
    }, 9462: function (e, t, s) {
        "use strict";
        s("275a")
    }, "96cb": function (e, t, s) {
        "use strict";
        s("f3ec")
    }, 9908: function (e, t, s) {
    }, "9b3f": function (e, t, s) {
        e.exports = s.p + "img/poster.005bcdf6.png"
    }, a1d8: function (e, t, s) {
        e.exports = s.p + "img/logo.dc3be0d4.png"
    }, a2ef: function (e, t, s) {
        e.exports = s.p + "img/sig-relay.d5d4c610.png"
    }, a328: function (e, t, s) {
        "use strict";
        s("8d76")
    }, a396: function (e, t, s) {
    }, a65e: function (e, t, s) {
        "use strict";
        s("e78d")
    }, a777: function (e, t, s) {
    }, a86b: function (e, t, s) {
        "use strict";
        s("854e")
    }, aa8b: function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAgCAYAAAAFQMh/AAAACXBIWXMAABYlAAAWJQFJUiTwAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAM6SURBVHgBrVddbtNAEJ7duFDES25AegLCCRpOQHuCphKoFCEllmgLT3WfUFMkJxJSaUFqcgJ6A9IbhBMQbuAnqNrYw0x+7c2s7SR8kiVn1zvfzs7sNxMFOeD5V8WHcFvWynmhACv0lBCgyHMKIEBQPX4Ao5sjd+86j02VRfhYD2pksD4hyoG+Uro9iAadD+5+HxYlbrQuawrRW4BwbgO04ZNDd78NeYnPmhc+EdbhP4AImgf1Pdcc1+ZAo3l5ZSENIsQWImyHGG0c1vcUP/xOz3PEqAPspQG2xTaFDc1g8TQgo62/sN703N0AMnDqn3sU42OBKOH5lLjhn1dB6Stjt/2IvElLEgkf/fNSQekf9FpK2ENwKeubU2Lpw2VJM8iDP/hgg09uGGNHOzvGB7AKKYPXcuyZLDZcfAS3w1AOiSmG1fgi+n2yCmmcnPMjPkbxr7E+OKf+xRYYR3zk7ns2Y3SEFUcVKHmwPLrjqgsYdmz39QEl5T3c1WCmB2MVJAlM7IgNWXDW+nrMcUNaMxMWWk9JyXPSGpdvAmLH4NjS5PrT+GBIu5cMcNbTsXlgAc/xaYhzhjNEuqlp9+X44Nra2i9psVKFHchAYRiCeUQQ9YyhEidXQovfvX35G8RdJ0MiI+nEBEKiFjXkR6ZqwQIFRZsGP33+9kT6UHG9zQR2pVEWE2MooKxOCnt4f/dMWjzA8AQyEBrZG0Mp+RN7GhFvEkOgK9JKilOXhQUsGItOW5orGIkZIfzUIWCiVaFU32FlkQywsJAM7qrZkQb8ztKYJjowpxVR11mH9R4pC8d5qixjPRUNjb1qQ06wsNBplKZbGCrjm2vNyiLpqZAQC4NtzInOqGEYFQnWUzCqCEvjKuSxsjjj5FI7Pq0h8chrMBOntCy5rRGIYlUv0fo0/C9NOueaaYiO3juovcq8TmntMPdr793X07ZqrsuknqlNRJIu97kEjotIf7Jz9k6DLlOF2tQQVaV2mBtByvpqfExsb22eLwPT0wlErT6kD/m+gtCuLgDOG1ciZaT+heHuRCn4DksAMdzm+2qbT61OeoXjVspJXWsl5m4iXw22ASu2jiSV2LF0E4ugkGLjH2itq+7TPZhxAAAAAElFTkSuQmCC"
    }, aea5: function (e, t, s) {
        "use strict";
        s("3cfd")
    }, b144: function (e, t, s) {
    }, b264: function (e, t, s) {
    }, b363: function (e, t, s) {
        "use strict";
        s("b144")
    }, b5cf: function (e, t, s) {
    }, b7e7: function (e, t, s) {
    }, b849: function (e, t, s) {
        "use strict";
        s("fbe1")
    }, b8dc: function (e, t, s) {
    }, b9e2: function (e, t, s) {
    }, ba16: function (e, t, s) {
    }, bb07: function (e, t, s) {
        "use strict";
        s("b5cf")
    }, be86: function (e, t, s) {
        "use strict";
        s("2daf")
    }, c0cf: function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAJDklEQVR4XuVbfVBU5Rp/XiqtqJvI9zeIFCgY3srK282Z+8/92z/8p3F0RJE/UGBdPlSWXYYgKTSvM3QRiiwSthYMcsUKSGAElTEiJmPow3IuV4ab8rWysCsLb/Mczzmty7J7ztk9e9d4ZnZg9zxf7++873ue53mfQ0BmopSuAIBAAHgcAB4BAPyOf7kPejBn9bnL/j8DALcIIfhdNiJyaKaUPgoAq9mB+7toYwyBAIBxQojJRV2LxN0KAKU0zE2DXmqcDBiEkBF3AeEWACilOMUjAMDPXY450TMBAP8lhODMcIlcAoBSugoAItm77pIjEoURgGFCyKREeZAEAKV0JQDEAgBOeW8gXBK/EkLMYp0RDQClNAgA1rC7ulh7cvLjU+MXQshvYoyIAoBSGs9OeTE2PM2LS+InoUYFA0ApfQ4AnhKq+P/MN0UI6RPigyAAKKX/EKLM23gIIRec+eQUAErpegAIdqbIS6//jxDyvSPfHAJAKQ0BgHVeOjihbg0SQkaXYl4SAErpXwDgeaFWvJzva0KIwZ6PdgGglGKi8iwAIAh/BsLBDxBCMOm6j5YCIMGLghx33YARQsiQUwDY8Pav7rLqZXq+sQ2bF80ASmmylNi+qanp2a1btw542YBt3cFM8jvrH+8DgM3qEABRVFRUVDw1NfU3Pz+/LrVaXSxKWCRzbW3tq4ODgztRLC4uTpeWlvalSBXfWWeRtgBslJLSKpXKzxYWFp5AR1atWtWp0WheF+mUIPa6urpX+vr6NJRSHxaAf+/bt++MIOE/mCYIIf3cVx4AtpiBm59oKi4uVk9MTGzhBP38/DrUanWJaEUOBLRa7ctXr14topQ+zLFt3rw5b9u2bYJCXhvVQ1xRxRqAFLaMJdpvi8VCSktL1ZOTk69ywqtXr/6qsLDwDdHK7AhotdoX2cFjPZGhjRs3vr5jx45OifqxvPYtyjIAUEofA4CXJSpjxEwmk09ZWZl6amrq75wef3//dpVKdcQVvTqd7oXe3l7NwsIC+shQSkpKyc6dOztc0QsAlwkhsxwAUQCw1kWFYDabHzpy5IgGN0SrmdBWWFhYJkV3Q0PDc1euXMHB+3LyGzZsKN21a5fTJEeAvZ8JIf/hAMDnPpa3XCaj0fhweXk5grDZaia0qlSqN8Uob2pqSunp6Sman59/kpNLTk5+IzU19SsxehzwThJCviFseYu/Y+5QbjAYHjl27JjGYDDwyyogIODLgoKCt4Tob25u3tDd3a2Zn5/nb0pycnJZampqmxB5ETw9CABWc58WISSI1WAwrGBBeMlqJnyhUqnKHSk4d+5cUldXl8ZiseC5AkNJSUlv7t69u1WQYXFMPyIAspW5bt269WhFRYXaYDC8yPkVEBDweUFBwVF7fp4/fz6xo6OjyGKxBHDX161b95aEYEcoDMMIAOb7mPfLQqOjo49VVlYiCJs4A4GBgS2HDx9+29pga2vrM+3t7UVzc3NYdGVo/fr15Xv27PlCFsfuKR1FACQ//4U6dvPmzcerqqrUd+7ceYGTCQoKOnfo0KHj+L2trS2+vb1dc/fu3VCrO380LS3tc6E2JPKNIwDoFL/TSlTkVGxkZMQXZ8L09DRfZAkMDNRv2rRJ39raqpmbmwvnlCQmJh7bu3fveadKXWe4gwDg4woPM2Wn4eHhJ6qrqzXT09N8uu3j4zPN5RHoQEJCwtvp6ektsjtzz4AJAcAY/iEPGYQbN248WVNTgyBg4nUfJSQk/Cs9PV3vKV8AYN7jAODgWlpaEi5cuHDUOrz19fUdKCkpOeDBwaMpBgCPLQG02NvbG9bc3KwxmUyLQu/g4OCmgwcPVngQBGYJeGQTxEH19fWFnDlzRjM7O8sHXitXrrxpNpv5DTAkJOTT/Pz8dzwEArMJyv4YxMEMDAwE6nQ6zczMTCI3uPj4+IotW7Z0abVatdFo5CtRoaGhjXl5eZUeAIF5DMoaCOEgBgcH/evr6zVGoxFPmRhau3btOxkZGZ9y1+vq6tQzMzNJ3PWQkJCG/Pz8kzKDwARCsoXC6PzQ0JDf6dOncfD8HY6Li1tUyrp27VqAVqvFGcKfRIWGhury8vKqZASBCYVx/T0jh5Hr168/derUKZzeuMy4O1+ZkZHRaM+evWUSFhb2SW5ubrUc/gHADwgAlplecbcBe0HPmjVrqvbv369zZKu/vz+ooaEBN0q+PhkeHv5xTk7Ou+72EQC6uYIIHoO52s7G+4ex/8mTJzHY4cPe2NjY6szMzE+EDKK/vz+4sbERlwM/M8PCwrS5ubnvCZEXyDNGCBngAMBeH0kVYVtjbAqMxRA++4uJiXk3KyvrY4GOMWz2Hpnh4eH1OTk5NWL0OOBlKsMcAJgL8CUsqQbGx8dXnDhxAlNfvhIUGxv7XmZmplaKTjZoUptMJtyoGYqIiKhTKpXvS9FnI3MJGy+ty+IuLQN7BdGYmJiarKyselecvXTpUrher0cQ+MgxIiLiI6VS+YELepnpj/JuORjBc4GSkhIshPIl8ejo6Pezs7PrXHCSF718+XLE2bNnMXzG7jSGIiMjaw8cOPChRP2LD0ZQEaVU0tFYaWlp3u3bt//JORMVFXVKoVCcluicXbGenp5IvV6vMZvN2J/IkMQSuf2jMRYAbHkVfTiqUCj4UnV0dPQH2dnZH7lz8JyuixcvRrW0tCAIMfiblM0VAJY+HGVBEH08fvz48R1jY2Mp/v7+3yoUilo5Bs/p7O7uju7s7HzNx8dnNioq6ur27dt7RNhzfDzOAoC1+OXbIMGCsHxbZFgAsBkaN0R8y+PPQNhH3G+vmdpRmxzW5/n09AFH4dpSTdTOGiVlTZU9BKrD5mkhrbIPUpO0LaZOm6adAsDuCcu3WZqD9AFrmnbaJM2NS9AMsALhQWiedtgcbbtGRAHALgfsH8aytrf1EWM/8I9LNUUvteGKBoAFAZup47yonxhfmrpurxna2ZNGEgBWS2J5vjZni+qyfXHSDhBYW8QIku/vcTYFRV4fB4DfvO7VWTtAYFMj1haw18fV9jt8K/Q2+87wrEjAnLK7tAc41X6vyoSJFYKBoIh5fR4Hi/m76LdBhfjF8fwOeh6r3KUY4QkAAAAASUVORK5CYII="
    }, c1f9: function (e, t, s) {
    }, c55f: function (e, t, s) {
    }, c80a: function (e, t, s) {
        e.exports = s.p + "img/close-mic.a488b182.png"
    }, c964: function (e, t, s) {
    }, cbc6: function (e, t, s) {
        "use strict";
        s("b9e2")
    }, d1fa: function (e, t, s) {
        "use strict";
        s("4213")
    }, d341: function (e, t, s) {
    }, d94e: function (e, t, s) {
    }, da66: function (e, t, s) {
        "use strict";
        s("2af2")
    }, de78: function (e, t, s) {
    }, e017: function (e, t, s) {
        "use strict";
        s("76e5")
    }, e3ec: function (e, t, s) {
        "use strict";
        s("53b9")
    }, e545: function (e, t, s) {
        "use strict";
        s("b7e7")
    }, e78d: function (e, t, s) {
    }, e8d4: function (e, t, s) {
    }, ebd9: function (e, t, s) {
    }, ed2c: function (e, t, s) {
    }, f3ec: function (e, t, s) {
    }, f6e3: function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAK4AAACpCAMAAAHy0MbyAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAJYUExURQrBYAvBYRPDZhzGbCXIcSrJdSHHbxjFaQ7CYwzBYSnJdEzSi3bcpqLnw87z3+X47uj58Ov68uz68+n58ef579/36rzu05Djt2TYmz7OghnFai/KeIHfrcPw2OP47fz+/f////P8+Nb15K7ry1vVlBrFaw7CYlDTjqrqyPP899j15YrhsxDCZFfUksDv1vr+/C7KdyzKdq3qynLbo2HXmf7//rTszhTDZo/jtk7SjCjJc7nt0fX9+THLes3y3/3//oXgsA3CYjTMe5HjuA/CY8/z4IDfrRbEaLft0GbYnO779Pb9+cLv1x3GbNL04ub575zmv5flvEDPgzDLeaXoxIzitPD79TXMfB7GbeL47F7WlhLDZavqyfj9+03SjN326YvitEbQh3jdqO/79CLHcLHrzE/TjSPHcOT47vH79rHrzUvSivf9+vn++8zy3lXUkdf15SDHblnVk+D36ybIclrVlGfZnHjdpznNf1HTjhfEadv26I3itUfRiMfx26Pow0HPhJPkuZjlvMHv1zjNfkTQhWnZnhvFa8ry3TLLepXkurbt0Gvan4LfrknRiV/Wl9X05HHbo7/v1Z3mv8jx24jhskjRiTrNf8nx3Mvy3WLXmUfQh0XQhu3681LTj9n15ljVk2XYm6/ry+H37Or68bXsz2PYmjfMfW3aoLvu0zbMfTPLe77u1S7KeErRirDrzNr253fcpkPPhSvJdafpxr3u1N736n/frPL89o7itW/boWDXl3ndqJvmvvv+/Xveqsbx2tz26fT8+BHDZD3OgYTgr4fhsQAAAKRC35oAAADIdFJOU/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8A9ppxxwAAAAlwSFlzAAAXEQAAFxEByibzPwAABVpJREFUaEPt2zuP2koUB3BaN1dCLikwols5fVb5Cim3QaxEtNomDdJKaHWbbaBASPcTUKVBShv6NNH6e2Uefxs/5u0H7NX5NXiOzwwjY+yxPR4N422MhYqEw/KFiDbiZ3UY0V7D1ThizDMiAmIcIkIURaycxlGEQCFLRvUvlDJ8/j+MP2OhTGymxoqxCDc2ioz6/mgo5hB1C39BtBZGkEFAQIiLERKiKN2xtc0ffpSkD1MsVhzxSZxgoydLlA2QKfyLmA7S4Buiaitk5RBWQ04BYTXkFBBWQ04BYTXkFA6IqyCl5AvWNGB9DVbWYW3NGWvVZGOZsb8X8i+MAiHe5rOFeYfMbcSuliQzlA3WSGUQ0fqGPAExHWRJvxFUwzk2h6gacnKIqiEnt0VYCTm5FcJKyMlVzrB1yMl9tFzTBn5ASgFxFWRc6I+YSCjDmoZGDzisq8PaKuWQR52qaTi/BKjB2jo28mZjb7k2TkRRLGv9lCcgTUdrkiQbpbZTVk5003VYH2X3lo4SQkiAN3EokhYbBNuqDFWkN6xqoTRWKmt5d2SJZhSQESQfAyohJwRa0ECSvzka0LhHmrcZGtBYI80b6usYR1kG7ARuRO1KH63d0D8GausFdfiAykbI9fAdNS0+Id2RU2cl1HCivHrQ8bgPihpuvqOSi/RxFRd4XSwyU1bEIndKUcVbym8fYplhBeNlswfWVIJuTXh35WIH7lhjvEHhAcEuRO9/ZKOrPSKEEEIIIVey+e+f8Wy8e3V4yuxuWb4AV02QCFO/rH9CvB3FLRPlbB5P6lsmWBnuBQ3VYXUwNNPg8NTPhA+o1Vr9eKb7UUgJUnt8WDFHTgDDXTkGSQHM97ncHt6ooAGN4NtctstZZHnbo74O0rxpHsgVkObtHvV1Qq+4be2G3hCndiVqV6J2pb7afUJ9HbcpYk316Xx15smAeqiuhzxPW9TWq8xQdmW9u+53J7VQ3Dc0CJgCa98KHJLd2c5tOaQ7QzUr46yoJpeNK3ndY85QyYXPcApV3KCOA9cfTZqglp1fu+7/utT9Z9NNoFJLj2s8nuBYZSxx1dKd919OTKxixLOiNC+OnnkJBdtUKzN+ALo8gub/byy2xVq6bMRKoR3WxekjllfsJw1+fl7H+pjEfHd65ztKV90did/q8hynw+lmomEIfi6mdEKr284nx02y7GlPU+4IIYQQQgghhBBisVxuNptOp4j0YpOt60/wF/MfN9nvHzt0UGH2OfTJUT9eTdMipPmt9Hhj2K4Vr6hwTYcFOuOiu3lUYZauWzZ31S3s90hD2F3vQKF4xdBudq3/3G90wFfoc+t2grYtN75Gf1/x5QEWaGJAL7YpBSah0w3C2SZWGI0HPzzoJ0+66GZ2rDuPV+tUWkyvDGKbvGOx7vbxn9UnfG+g1cDnirbdfUE7A6Hu9om62yfqbp+ou336YN1tMTjn4oFPwm5TR/U6m+flJOCKvSr8vZQA5ZlggQa8/jn7TMTU8ZpZ20ary7SLfMpiv9J2V2klsfuE3WBHfFcnMjTal32buwsKcfgbcQ463bRSfxeZ6Rpf0a2A9xtcTLo4fKn0soH3fAp6P/o4xfWw3+amPfzhetpzuR2+olv3fMp89+56HJ1F+4kCdpQTihVnuc/HB5TLDpOB75VJE9GjZKX6cow1Bx0xWkR4A+AB5ZIU58Gejq5hnnGUaxxCMdacnlC+EZP8qHwqvwyB/ZZF0xt7RSJdF4eN6Wl7PP7asoHmFNv2J5JuyVk3Wnu8yt/fKkq32Jwlq/tbflMmet/e/ZEdncZfj0Gvvw4s35ztXpkjhBBCCCGkrdHoLzv9X1u4OdoGAAAAAElFTkSuQmCC"
    }, f7a2: function (e, t, s) {
        "use strict";
        s("4602")
    }, fb0d: function (e, t, s) {
    }, fbe1: function (e, t, s) {
    }, fc3e: function (e, t, s) {
    }, fcad: function (e, t) {
        e.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAMWSURBVHgBrVeLUdtAEH13OGQIMCNVEFFBTAVABSEVhFQAqcB2BZgKgApCKgAqiFOB3YHEBONAQJu3AoM+J+sMfjOasU+6fXt3+3ln4Ik4lgBL2LVL2BKgDUEEgyB7KUj4e2SAQSq4xD+chaFJfOwaD+LIvse+pNh7JvQzfJLeoUdHRg3f1RIHJO6I4ABvgDHop7eZI4m3A7pqs4xz/oywGIzkDjuu3bAO8vYM8oRH0eOzIxYbwaox+sgHhNmY4EhjwTEvW1B8LW3Mgq48GcuQjxSeGxnGf2QbniDRns6p2KFt5agjD1zkNNaPRbyDL2+PTvdrnKjau7qRwwr5WLp4I+hEt2xXucreRq6VY0Fw7cT0KLIgtMvoFGZoIK2ii0XhnrbKwfnuMb2NngcjNM6/YzR/C9fMCRYIDWJjs+yaImFqblh6slv4kp42kV+N5bg2mmsQrpsLJc0NBcptrcFW/kPm888mY+wFe9y1X/GNzFUlafso/1+5Lbe7XbJ+Bj8ERnA4M7eruChSoW255VHhk3sMMB+0yg01tZz5nUerUiUjoymRH9HSigaU5+Qw4pverBgqz7VYJNR1cfaCWqgDhTY5EfmIV0Abkaxg8ynanXAcUWLLHv+9xgbmYsaFdkJu+0FoGlRQC+VuOLI8kN+FIYtt+CHhqr8Ha2Zn1qpLKNQclXAWaTE1OLjfaEZXbbnda2aufsFK+Dn/X/XjtBQPgRe9l22p/6q8oBqB+XWcH2MpDq1qNRKeoujpcWNOz0OuizTFhqeiVbkf07CF8laqEu5gQXiyFeXHUote4SNXz76ayJudYOPqeGmNTJI5dJxXiXVA57hUVqYv6yRePKEyqhOTDCJ4Qnt/rbidFBtXpe6rdGYQ/siuXlWMGExn6QNbNpvW9LKRGb1n3LR4bXug0nHdoKgzGOxfmLqDmQ5MDbLVntc4MT+U3DC1VzwuJgr9UG6xWRYQr8Fzj1hx3xGbL6e6vQ/ocuu/wh9apk95m+7XEXs78OyIRu44q+XbnPTpSchMzzrRpkYnB4yPS6zzem78ruf/AZwvhwB6dxJtAAAAAElFTkSuQmCC"
    }, fee6: function (e, t, s) {
    }, ffa6: function (e, t, s) {
        "use strict";
        s("7dce")
    }
});