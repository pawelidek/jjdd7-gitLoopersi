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
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="/css/calendar.css">
  <link rel="stylesheet"
        href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

</head>
<body>

<#include "/side_and_navbar${userType}.ftlh">

<#include "/content${function}.ftlh">

<#include "calendar.ftlh">
<#include "footer.ftlh">

<script src="/js/bootstrap.js"></script>
<script src="/js/feather.js"></script>
<script src="/js/calendar.js"></script>

</body>
</html>