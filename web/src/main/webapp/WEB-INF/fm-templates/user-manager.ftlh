<!DOCTYPE html>
<header>
  <title>User manager @admin - EVC</title>
</header>
<body>

<#list employees>
  <ul>
    <#items as employee>
      <li><p>${employeeList}</p></li>
    </#items>
  </ul>
</#list>

</body>
</html>