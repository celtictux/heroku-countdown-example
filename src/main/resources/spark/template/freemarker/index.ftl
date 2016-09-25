<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="jumbotron text-center">
  <div class="container">
    <a href="/" class="lang-logo">
      <img src="/logo.png">
    </a>
    <h1>Countdown Timer</h1>
    <h2>Next anticipated Bobism</h2>
    <h2>${countdown}</h2>
  </div>
</div>
<div class="container">
  <div class="alert alert-info text-center" role="alert">
    How I got here: <a href="https://devcenter.heroku.com/articles/getting-started-with-java" class="alert-link">Getting Started with Java on Heroku</a> tutorial.
  </div>
  <hr>
  <div class="row">
    <div class="col-md-6">
      <h3><span class="glyphicon glyphicon-link"></span> Helpful Links</h3>
      <ul>
        <li><a href="https://www.heroku.com/home">Heroku</a></li>
        <li><a href="https://devcenter.heroku.com/">Heroku Dev Center</a></li>
        <li><a href="https://devcenter.heroku.com/articles/getting-started-with-java">Getting Started with Java on Heroku</a></li>
        <li><a href="https://devcenter.heroku.com/articles/deploying-java">Deploying Java Apps on Heroku</a></li>
      </ul>
    </div>
  </div> <!-- row -->
</div>
</body>
</html>
