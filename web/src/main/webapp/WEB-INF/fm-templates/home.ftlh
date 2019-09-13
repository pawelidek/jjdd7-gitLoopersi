<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="/img/icon.ico">
  <title>Home Page</title>
  <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/dashboard.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <#include "/side_and_navbar${userType}.ftlh">
</div>
<div class="container">
    <#include "/content${function}.ftlh">
</div>
<div class="container">
    <#include "/calendar.ftlh">
</div>
<#include "/footer.ftlh">
<script src="/js/bootstrap.js"></script>
<script src="/js/feather.js"></script>
</body>
</html>