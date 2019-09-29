<nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/home"><img
        src="/img/logo.png" alt="Responsive image"
        width=150px height=65px></a>

  <div class="navbar-nav px-3 nav-item text-nowrap">
    <table class="table table-borderless" style="margin-bottom: 0">
      <tr>
        <td style="text-align: right">
          <a class="nav-link " href="#" onclick="window.location.href = '/logout';">
            <button type="button" class="btn btn-outline-secondary btn-sm">
              <img src="/img/button_logout.svg" alt="Log Out"> Log out
            </button>
          </a>
        </td>
      </tr>
    </table>
  </div>
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
            <a class="nav-link" href="/admin/employee">
              Employee Manager
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/team">
              Team Manager
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/vacation">
              Vacation Manager
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/statistics">
              Vacation Statistics
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/holiday">
              Holiday Manager
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/search/holiday">
              Holiday Search
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/jsonLoader">
              Load Holidays from JSON File
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</div>