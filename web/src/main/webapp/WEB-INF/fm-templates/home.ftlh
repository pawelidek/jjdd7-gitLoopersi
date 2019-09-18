<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="google-signin-client_id" content="147273613156-lfrmpudlegslf28hs16mnsvdbjtqdbev.apps.googleusercontent.com">
  <link rel="icon" href="/img/icon.ico">
  <title>Home Page</title>
  <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/dashboard.css" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="/css/calendar.css">
</head>
<body>

<#include "/side_and_navbar${userType}.ftlh">

<#include "/content${function}.ftlh">

<#include "calendar.ftlh">
<#include "footer.ftlh">

<script src="/js/bootstrap.js"></script>
<script src="/js/feather.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/holidayManagment.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>



</body>
</html>