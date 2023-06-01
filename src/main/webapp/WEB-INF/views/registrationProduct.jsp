<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/head.jsp"%>
  <title>Product Registration Page</title>
  <style>
    .row-margin {
      margin-bottom: 10px;
    }
  </style>
</head>
<%@include file="./includes/navbar.jsp"%>
<body>
  <div class="container">
    <h2>Product Registration Form</h2>
    <form action="registrationProductAction" method="post" enctype="multipart/form-data">
      <div class="row row-margin">
        <div class="col-md-4">
          <input type="text" class="form-control" placeholder="Name" name="name">
        </div>
        <div class="col-md-3">
          <input type="text" class="form-control" placeholder="Category" name="category">
        </div>
        <div class="col-md-2">
          <input type="text" class="form-control" placeholder="Price" name="price">
        </div>
        <div class="col-md-2">
          <input type="file" class="form-control-file" name="image">
        </div>
        <div class="col-md-1">
          <button type="button" class="btn btn-primary btn-sm" onclick="addRow()">Add</button>
        </div>
      </div>
      <div id="dynamicRows"></div>
      <button type="submit" class="btn btn-success">Submit</button>
    </form>
    <%@include file="./includes/footer.jsp"%>
  </div>

  <script>
    var rowCounter = 0;

    function addRow() {
      var html = `
        <div class="row row-margin">
          <div class="col-md-4">
            <input type="text" class="form-control" placeholder="Name" name="name${rowCounter}">
          </div>
          <div class="col-md-3">
            <input type="text" class="form-control" placeholder="Category" name="category${rowCounter}">
          </div>
          <div class="col-md-2">
            <input type="text" class="form-control" placeholder="Price" name="price${rowCounter}">
          </div>
          <div class="col-md-2">
            <input type="file" class="form-control-file" name="image${rowCounter}">
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">Remove</button>
          </div>
        </div>
      `;
      document.querySelector('#dynamicRows').insertAdjacentHTML('beforeend', html);
      rowCounter++;
    }

    function removeRow(btn) {
      btn.closest('.row-margin').remove();
    }
  </script>
</body>
</html>
