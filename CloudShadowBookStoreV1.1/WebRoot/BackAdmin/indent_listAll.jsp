<%@ page language="java" import="java.util.*,com.imu.csbookstore.javabean.*,com.imu.csbookstore.util.*,
com.imu.csbookstore.service.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="BackAdmin/images/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="BackAdmin/css/css.css" rel="stylesheet" type="text/css" />
<link href="BackAdmin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="BackAdmin/js/xiangmu.js"></script>
<SCRIPT language=JavaScript>
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function link(){
    document.getElementById("fom").action="xiangmu.htm";
   document.getElementById("fom").submit();
}

function on_load(){
	var loadingmsg=document.getElementById("loadingmsg");
	var mainpage=document.getElementById("mainpage");
	loadingmsg.style.display="";
	mainpage.style.display="none";
	
	loadingmsg.style.display="none";
	mainpage.style.display="";
}
</SCRIPT>
  </head>
<body>
<%
	IndentService indentService=new IndentService();
	    int intPageSize; //一页显示的记录
        int intRowCount; //记录总数
        int intPageCount; //总页
        int intPage; //待显示页
        //设置一页显示的记录
        intPageSize = 4;
    //取得待显示页
        String strPage = request.getParameter("page");
        if (strPage == null) {//表明在QueryString中没有page这一个参数，此时显示第一页数�
            intPage = 1;
        } else {//将字符串转换成整�
            intPage = java.lang.Integer.parseInt(strPage);
            if (intPage < 1) {
                intPage = 1;
            }
        }
        intRowCount=indentService.getIndentTotalNum();
        //记算总页
        intPageCount = (intRowCount + intPageSize - 1) / intPageSize;
   	 //调整待显示的页码
        if (intPage > intPageCount&&intPageCount>0) {
            intPage = intPageCount;
        }
       if(intPageCount==0){
        intPage=1;
        intPageCount=1;
        }
        int begin=(intPage - 1) * intPageSize ;
	List list=indentService.listAllIndentOrderByIndentId(begin, intPageSize); 
 %>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="BackAdmin/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="BackAdmin/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">查看订单</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="BackAdmin/images/mail_rightbg.gif"><img src="BackAdmin/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="BackAdmin/images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">
    </br>
    </br>
    
      <form name="fom" id="fom" method="post" action="">
<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">选择：
             <a href="javascript:void(0);" class="right-font08" onClick="selectAll();">全选</a>
             <a href="javascript:void(0);" class="right-font08" onClick="unselectAll();">反选</a>
               </span>
	              <input name="Submit2" type="button" class="right-button08" value="刷新" onclick= "fom.action='BackAdmin/indent_listAll.jsp';fom.submit(); "/></td>
          	 </tr>
              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
					                  <tr>
                    <td height="20" colspan="13" align="center" bgcolor="#EEEEEE"class="tablestyle_title"><span align="center">订单列表</span></td>
                    </tr>
                    <tr>
				    <td width="35" align="center" bgcolor="#EEEEEE">选择</td>
                    <td width="90" bgcolor="#EEEEEE"><div align="center">订单ID</td>
                    <td width="100" bgcolor="#EEEEEE"><div align="center">会员名</td>
                    <td width="300" bgcolor="#EEEEEE"><div align="center">配货信息</td>
                    <td width="150" bgcolor="#EEEEEE"><div align="center">下单时间</td>
                    <td width="60" bgcolor="#EEEEEE"><div align="center">订单价格</td>
                    <td  width="200" bgcolor="#EEEEEE"><div align="center">订单状态</td>
                    <td width="282" align="center" bgcolor="#EEEEEE">操作</td>
                  </tr> 
                  

   <%
        if(list!=null){
        	for(int i=0;i<list.size();i++){
        		Indent indent= (Indent)list.get(i);
        		int user_id = indent.getUser_id();
        		int delivery_id=indent.getDelivery_id();
            UserService userService = new UserService();
            DeliveryService deliveryserivice =new DeliveryService();
            String user_name=userService.getUserNameByUserId(user_id);
            String str =deliveryserivice.getDeliveryInfoByDeliveryId(delivery_id);
    %>
     <tr align="center">
				   <td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>

                    <td bgcolor="#FFFFFF"><%=indent.getIndent_id() %></td>
                    <td bgcolor="#FFFFFF"><%=user_name %></td>
                    <td bgcolor="#FFFFFF"><%=str %></td>
                    <td bgcolor="#FFFFFF"><%=indent.getIndent_date() %></td>
                    <td bgcolor="#FFFFFF"><%=indent.getIndent_price() %></td>
                    <td bgcolor="#FFFFFF">

    		<%
    			if(indent.getIndent_state()==1){ 
    		%>
    		<div align="center">
    			未支付
    		</div></td>
    		<td bgcolor="#FFFFFF"><div align="center">
    		    <a href="IndentServlet?type=admin_confirmPay&&indent_id=<%=indent.getIndent_id() %>&&page=<%=intPage %>">确认支付|</a>
   		  &nbsp;<a href="IndentServlet?type=admin_cancel&&indent_id=<%=indent.getIndent_id() %>&&page=<%=intPage %>">取消订单|</a>
   		  &nbsp;<a href="IndentServlet?type=delete&&indent_id=<%=indent.getIndent_id()  %>&&page=<%=intPage %>">删除</a> 
   		   </div></td>
    	<%}else if(indent.getIndent_state()==2) { %>
    		<div align="center">
    			已支付，等待发货
      		</div></td>
    		<td bgcolor="#FFFFFF"><div align="center"><a href="IndentServlet?type=deliverGoods&&indent_id=<%=indent.getIndent_id() %>&&page=<%=intPage %>">发货|</a>
   		  &nbsp;<a href="IndentServlet?type=admin_cancel&&indent_id=<%=indent.getIndent_id() %>&&page=<%=intPage %>">取消订单|</a>
   		  &nbsp;<a href="IndentServlet?type=delete&&indent_id=<%=indent.getIndent_id()  %>&&page=<%=intPage %>">删除</a> </div></td>
    	<%}else if(indent.getIndent_state()==3) { %>
    		<div align="center">
    			已取消
      		</div></td>
    		<td bgcolor="#FFFFFF"><div align="center">
   		  <a href="IndentServlet?type=delete&&indent_id=<%=indent.getIndent_id()  %>"&&page=<%=intPage %>>删除</a> </div></td>
   		  <%}else if(indent.getIndent_state()==4) { %>
   		  <div align="center">
    			已发货，等待确认
      		</div></td>
    		<td bgcolor="#FFFFFF"><div align="center">
   		  <a href="IndentServlet?type=delete&&indent_id=<%=indent.getIndent_id()  %>&&page=<%=intPage %>">删除</a> </div></td>
    	  <%}else if(indent.getIndent_state()==5) { %>
    	  <div align="center">
    			已完成
      		</div></td>
    		<td bgcolor="#FFFFFF"><div align="center">
   		  <a href="IndentServlet?type=delete&&indent_id=<%=indent.getIndent_id()  %>&&page=<%=intPage %>">删除</a> </div></td>
   		  <% }%>
    	</tr>
    	<%} } %>
		
   </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="BackAdmin//images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
                <td width="50%">共 <span class="right-text09"><%=intPageCount %></span> 页 | 第 <span class="right-text09"><%=intPage %></span> 页|共<span class="right-text09"><%=intRowCount %></span>条记录</td>
                <td width="49%" align="right">[<a href="BackAdmin/indent_listAll.jsp?page=1" class="right-font08">首页</a> | <a href="BackAdmin/indent_listAll.jsp?page=<%=intPage - 1%>" class="right-font08">上一页</a> | <a href="BackAdmin/indent_listAll.jsp?page=<%=intPage + 1%>" class="right-font08">下一页</a> |
                 <a href="BackAdmin/indent_listAll.jsp?page=<%=intPageCount %>" class="right-font08">末页</a>] 转至：</td>
                <td width="1%"><table width="38" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="39%"><input name="pageToJump" type="text" class="right-textfield03" size="1" /></td>
                      <td width="61%"><input name="Submit23222" type="submit" class="right-button06" value=" " onclick= "fom.action='IndentServlet?type=jump';fom.submit(); "/>
                      </td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>

<div id="loadingmsg" style="width:100px; height:18px; top:0px; display:none;">
	<img src="BackAdmin/images/loadon.gif" />
</div>

    	</td>
    <td background="BackAdmin/images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="BackAdmin/images/mail_leftbg.gif"><img src="BackAdmin/images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="BackAdmin/images/buttom_bgs.gif"><img src="BackAdmin/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="BackAdmin/images/mail_rightbg.gif"><img src="BackAdmin/images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
</html>
