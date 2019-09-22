<nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><img
        src="/img/logo.png" alt="Responsive image"
        width=150px height=65px></a>

  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link " href="#"  onclick="window.location.href = '/googlelogin';" >Log in</a>
<#--      <div class="g-signin2" data-onsuccess="onSignIn" data-theme="light"  onclick="window.location.href = '/googlelogin';"></div>-->
<#--      <script>-->
<#--        function onSignIn(googleUser) {-->
<#--          // Useful data for your client-side scripts:-->
<#--          var profile = googleUser.getBasicProfile();-->
<#--          console.log("ID: " + profile.getId()); // Don't send this directly to your server!-->
<#--          console.log('Full Name: ' + profile.getName());-->
<#--          console.log('Given Name: ' + profile.getGivenName());-->
<#--          console.log('Family Name: ' + profile.getFamilyName());-->
<#--          console.log("Image URL: " + profile.getImageUrl());-->
<#--          console.log("Email: " + profile.getEmail());-->

<#--          // The ID token you need to pass to your backend:-->
<#--          var id_token = googleUser.getAuthResponse().id_token;-->
<#--          console.log("ID Token: " + id_token);-->

<#--        }-->
<#--      </script>-->


    </li>
  </ul>
</nav>

<div class="container-fluid">

  <div class="row">
    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
      <div id="menu" class="sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="/home">
              Main Page
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              Holidays
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              Change date format
            </a>
          </li>

        </ul>
      </div>
    </nav>
  </div>
</div>
