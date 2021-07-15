// JScript 文件

  function chkemail() //email验证
      {

        var temp = document.getElementById("email");
       
       
          //对电子邮件的验证
           var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
           if(temp.value=="")
           {
            return 0;
           }
           else
           {
               if(!myreg.test(temp.value))
               {
                  return 2;
               }
                else 
               {
              
                 url="checkName.aspx?UName=" + $("#email").val().replace("@","_");
	            
			        if(doRequest(url)==0)
			        {
        			 
			         return 1;
			        }
			        else
			        {
        			
			        return 3;
			        }

               }
           }


      }


 function chkcomname()    //公司名称
 {

	var comname = $.trim($("#comname").val());
	
	if(comname=="")
	 {
		return 0;
	}
	else
	{
		return 1;	
	}
}

function chklinkname()    //公司联系人
 {

	var linkname = $.trim($("#linkname").val());
	
	if(linkname=="")
	 {
		return 0;
	}
	else
	{
		return 1;	
	}
}
      
      function chklinkphone()    //联系电话
 {

	var linkphone = $.trim($("#linkphone").val());
	
	if(linkphone=="")
	 {
		return 0;
	}
	else
	{
		return 1;	
	}
}

	
	function chkAuthcode(){    //验证码
	authcode=$.trim($("#txtCode").val());
	if(authcode=="") return 0;
	return 1;
	}
 //------------------------------------------------------
 //------------                      -----------------------
 //-------------    完美的分割线   ---------------------
 //-------------                    ---------------------
 //  ------------------------------------------------------   
      
      
      
  $(document).ready(function(){    
//-------------------邮件验证-------------------------
$("#email").bind("focus", function()
	{

		$("#li_email").attr("class","msghover2");
		
		$("#div_email_ok").hide();
		$("#div_email_roule").show();
		$("#div_email_err").hide();
		
	});
	
	$("#email").bind("blur",function(){
	$("#li_email").attr("class","");
		var ret=chkemail();
	
		
		if(ret==0)
		{
			
			
			$("#div_email_err").show();
			if($("#div_email_err").is(":visible"))
			{
				$("#div_email_ok").hide();
				$("#div_email_roule").hide();
				$("#div_email_err").html("E-Mail地址不能为空");
			}
			return false;
		}
		else
		{
		  if(ret==1)
		  {
			    $("#div_email_ok").show();
			    if($("#div_email_ok").is(":visible"))
			    {
				    $("#div_email_err").hide();
				    $("#div_email_roule").hide();
    				
    				
			    }
			    return true;
			}
			else if(ret==2)
			{
			   $("#div_email_err").show();
			    if($("#div_email_err").is(":visible"))
			    {
				    $("#div_email_ok").hide();
				    $("#div_email_roule").hide();
				    $("#div_email_err").html("E-Mail地址格式不正确");
			    }
			return false;
			}
			else if(ret==3)
			{
			  $("#div_email_err").show();
			    if($("#div_email_err").is(":visible"))
			    {
				    $("#div_email_ok").hide();
				    $("#div_email_roule").hide();
				    $("#div_email_err").html("该E-Mail已经注册");
			    }
			return false
			}
		
		}
		
	});
	//-------------------end--------------------------
	
	/** ----------- 密码输入框事件 ----------- */
	$("#pwd").bind("focus", function(){
		//check account radio
		
		 $("#li_pwd").attr("class","msghover");
				$("#password_ico_ok").hide();
				$("#password_ico_err").hide();
			
			$("#div_password_rule").show();
			
			chkPasswordStrong($("#pwd").val());
	
		
	});
	$("#pwd").bind("blur", function(){
	 $("#li_pwd").attr("class","");
		var  pwdret = chkPassword();
		
		if(pwdret>0){
		
			$("#password_ico_ok").show();
			$("#password_ico_err").hide();
			$("#div_password_rule").hide();
		
			//$("#div_password_err_info").html("");
		}
		else {
			    if(pwdret==0){
    			 $("#span_passowrd_Strong").hide();
			    $("#password_ico_ok").hide();
			    $("#password_ico_err").show();
			    $("#div_password_rule").hide();
    			
			    $("#password_ico_err").html("密码不能为空");
		    }
		    else if(pwdret==-1){
    		
			    $("#password_ico_ok").hide();
			    $("#password_ico_err").show();
			    $("#div_password_rule").hide();
    			
			    $("#password_ico_err").html("请输入6～16位字符的密码");
    			
		    }
		 

		}
	});
	$("#pwd").bind("keyup", function(){
	
		//检查密码强度
		chkPasswordStrong($("#pwd").val());
	
	});
	
		$("#passwordconfirm").bind("focus", function(){
		
		$("#li_passwordconfirm").attr("class","msghover2");
				$("#passwordconfirm_ico_ok").hide();
				$("#passwordconfirm_ico_err").hide();
			
			$("#passwordconfirm_ico_roule").show();
			
	
		
	});
	
	$("#passwordconfirm").bind("blur",function(){
		$("#li_passwordconfirm").attr("class","");
		var chk=chkPasswordconfirm();
			if(chk==1){
			 $("#passwordconfirm_ico_err").hide();
		    $("#passwordconfirm_ico_ok").show();
		    $("#passwordconfirm_ico_roule").hide();
	           
            	
	            }
	    else if(chk==0){
	        $("#passwordconfirm_ico_roule").hide();
		        $("#passwordconfirm_ico_err").show();
		        $("#passwordconfirm_ico_ok").hide();
		        $("#passwordconfirm_ico_err").html("确认密码不能为空")
        	
	    }
	    else {
	   
			     $("#passwordconfirm_ico_roule").hide();
		            $("#passwordconfirm_ico_err").show();
		            $("#passwordconfirm_ico_ok").hide();
		            $("#passwordconfirm_ico_err").html("密码不匹配");
		
	        }
	});
	/** --------- end ------------ */
	
	
//------------公司名称-----------------
	
	$("#comname").bind("focus", function()
	{
	
	
		
		$("#div_comname_ok").hide();
		$("#div_comname_roule").show();
		$("#div_comname_err").hide();
		
	});
	
	//鼠标离开验证输入信息
	$("#comname").bind("blur",function(){
	$("#li_comname").attr("class","");
		var ret=chkcomname();
	
		
		if(ret==0)
		{
		
			
			$("#div_comname_err").show();
			if($("#div_comname_err").is(":visible"))
			{
				$("#div_comname_ok").hide();
					$("#div_comname_roule").hide();
				
			}
			return false;
		}
		else
		{
		
			$("#div_comname_ok").show();
			if($("#div_comname_ok").is(":visible"))
			{
				$("#div_comname_err").hide();
				$("#div_comname_roule").hide();
				
				
			}
			return true;
		
		}
		
	});
//-------------end----------------
	
       /**-------联系人---------*/ 
$("#linkname").bind("focus", function()
	{
		$("#li_linkname").attr("class","msghover2");
		
		$("#div_linkname_ok").hide();
		$("#div_linkname_roule").show();
		$("#div_linkname_err").hide();
		
	});
	
	//鼠标离开验证输入信息
	$("#linkname").bind("blur",function(){
	$("#li_linkname").attr("class","");
		var ret=chklinkname();
	
		
		if(ret==0)
		{
			
			
			$("#div_linkname_err").show();
			if($("#div_linkname_err").is(":visible"))
			{
				$("#div_linkname_ok").hide();
					$("#div_linkname_roule").hide();
				
			}
			return false;
		}
		else
		{
		
			$("#div_linkname_ok").show();
			if($("#div_linkname_ok").is(":visible"))
			{
				$("#div_linkname_err").hide();
				$("#div_linkname_roule").hide();
				
				
			}
			return true;
		
		}
		
	});

	/**--------------end-------------------*/
	
	//-----------联系电话---------------------
      /**-------联系人---------*/ 
$("#linkphone").bind("focus", function()
	{
		$("#li_linkphone").attr("class","msghover2");
		
		$("#div_linkphone_ok").hide();
		$("#div_linkphone_roule").show();
		$("#div_linkphone_err").hide();
		
	});
	
	//鼠标离开验证输入信息
	$("#linkphone").bind("blur",function(){
	$("#li_linkphone").attr("class","");
		var ret=chklinkphone();
	
		
		if(ret==0)
		{
			
			
			$("#div_linkphone_err").show();
			if($("#div_linkphone_err").is(":visible"))
			{
				$("#div_linkphone_ok").hide();
					$("#div_linkphone_roule").hide();
				
			}
			return false;
		}
		else
		{
		
			$("#div_linkphone_ok").show();
			if($("#div_linkphone_ok").is(":visible"))
			{
				$("#div_linkphone_err").hide();
				$("#div_linkphone_roule").hide();
				
				
			}
			return true;
		
		}
		
	});
	//---------------end----------
	
		/** --------- 验证码 ------------ */
	$("#txtCode").bind("focus", function()
	{
		$("#li_txtCode").attr("class","msghover2");
		
	
		$("#div_txtCode_err").hide();
		
	});
	
	
	$("#txtCode").bind("blur",function(){
	
$("#li_txtCode").attr("class","");
		var chkret=chkAuthcode();
		
		if(chkret==0){
			//$("#authcode_ico_ok").hide();
			$("#div_txtCode_err").show();
			$("#div_txtCode_err").html("验证码不能为空");
		}
		
	});
	
	/** --------- end ------------ */
	
	
	
	});
	
	


function chkPasswordconfirm(){
	var password= $("#pwd").val();
	var passwordconfirm = $("#passwordconfirm").val();
	
	
	if(passwordconfirm==''){
	
		return 0;
	}
	else if(passwordconfirm==password){
	
		return 1;
	}
	else {
		
		return 2;
	}

}

/**
 * 显示密码强弱
 * @return
 */
function chkPasswordStrong(me) {
	//恢复重复输入密码状态
	 $("#div_passowrd_Strong").show();
	
	$("#li_pwd").attr("class","msghover");
    $("#div_password_rule").show();
	
	$("#password_ico_ok").hide();
	$("#password_ico_err").hide();
	
	$("#div_password_rule").show();
	var csint = Evaluate(me);
 
	$("#span_passowrd_Strong").show();

  $("#span_passowrd_Strong").attr("class","status_"+csint);
   
}


//强度函数
function Evaluate(word)
{
    return word.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$/g, "$1$2$3$4$5").length;
}


function chkPassword(){

	password= $("#pwd").val();
	
	if(password == "") return 0;
	
	var len;
	var i;
	var isPassword = true;
	len = 0;

	
	if(password.length > 16 || password.length < 6)
		return -1;
	
	return 1;
}

//--------------------------------------------
//----------                      ---------------
//----------    完美的分割线       ----------
//---------                        -------------
//-------------------------------------------




function chk()
{
   var ok=true;
   var ret;
     ret=chkemail()//邮箱
     if(ret!=1)
     {
       if(ret==0)
       {
         $("#div_email_err").show();
			if($("#div_email_err").is(":visible"))
			{
				$("#div_email_ok").hide();
				$("#div_email_roule").hide();
				$("#div_email_err").html("E-Mail地址不能为空");
			}
			ok= false;
       }
       else if(ret==2)
       {
          $("#div_email_err").show();
			    if($("#div_email_err").is(":visible"))
			    {
				    $("#div_email_ok").hide();
				    $("#div_email_roule").hide();
				    $("#div_email_err").html("E-Mail地址格式不正确");
			    }
			    	ok= false;
       }
       else if(ret==3)
       {
       
            $("#div_email_err").show();
			    if($("#div_email_err").is(":visible"))
			    {
				    $("#div_email_ok").hide();
				    $("#div_email_roule").hide();
				    $("#div_email_err").html("E-Mail已注册");
			    }
			    	ok= false;
       
       }
      }
      
      	ret = chkPassword();  //密码
	if(ret<1){
		ok=false;
	
		$("#password_ico_ok").hide();
		$("#password_ico_err").show();
		$("#div_password_rule").hide();
		$("#password_ico_err").html("请输入6～16位字符的密码");
	}
	else {
		if(!chkPasswordconfirm()){
			ok=false;
			$("#div_passwordconfirm_err").show();
			$("#passwordconfirm").attr("class","inp ipt-error");
			$("#passwordconfirm_ico_ok").hide();
			$("#passwordconfirm_ico_err").show();
		}
		else if($.trim($("#pwd").val())==$.trim($("#username").val()) 
				|| $.trim($("#pwd").val())==($.trim($("#username").val())+$.trim($("#domain").val()))){
			//检查用户名与密码是否相同
			ok=false;
			
			$("#password_ico_ok").hide();
			$("#password_ico_err").show();
			$("#div_password_rule").hide();
			$("#div_password_err").show();
			$("#div_password_err_info").html("输入的密码不能与用户名一样");
		}
		
	}
	
      ret=chkcomname();//公司名称
     if(ret==0)
     {
        
			$("#div_comname_err").show();
			if($("#div_comname_err").is(":visible"))
			{
				$("#div_comname_ok").hide();
					$("#div_comname_roule").hide();
				
			}
			 ok= false;
     }
          ret=chklinkname();//联系人
     if(ret==0)
     {
        $("#div_linkname_err").show();
			if($("#div_linkname_err").is(":visible"))
			{
				$("#div_linkname_ok").hide();
					$("#div_linkname_roule").hide();
				
			}
			ok= false;
     }
      ret=chklinkphone();//联系电话
     if(ret==0)
     {
       $("#div_linkphone_err").show();
			if($("#div_linkphone_err").is(":visible"))
			{
				$("#div_linkphone_ok").hide();
					$("#div_linkphone_roule").hide();
				
			}
			ok= false;
     }
     return  ok;
}