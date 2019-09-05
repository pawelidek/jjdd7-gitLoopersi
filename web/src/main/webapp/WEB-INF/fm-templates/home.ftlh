<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="https://getbootstrap.com/docs/4.1/assets/img/favicons/favicon.ico">
  <title>Dashboard Template for Bootstrap</title>
  <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/dashboard.css" rel="stylesheet">
</head>
<body>

    <#include "/side_and_navbar${userType}.ftlh">

<#--<#if userType == "guest"><#include "/side_and_navbarGuest.ftlh">-->
<#--<#elseif userType == "user"><#include "/side_and_navbarUser.ftlh">-->

<#--<#elseif userType == "admin"><#include "/side_and_navbarAdmin.ftlh">-->
<#--</#if>-->

<#include "/content.ftlh">

<div id="calendar">
  <script type="text/javascript"
          src="//www.kalendarznastrone.pl/calendar/start.php?theme=smoothness&font_size=12"></script>
</div>
<#include "/footer.ftlh">

</div>
</div>


<script src="/js/bootstrap.js"></script>
<script src="/js/feather.js"></script>
<script>
  feather.replace()
</script>


</body>
</html>