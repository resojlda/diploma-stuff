<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Chart</title>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!--   <script defer type="text/javascript" src="chart.js"></script> -->
    <spring:url value="/resources/js/chart.js" var="chart" />
    <script src="${chart}"></script>
  <script>


google.charts.load('current', {'packages':['corechart']});

function drawPieChart(data, options, element) {
    var chart = new google.visualization.PieChart(element);
    chart.draw(google.visualization.arrayToDataTable(data), options);
}

function drawColumnChart(data, options, element){
  var chart = new google.visualization.ColumnChart(element);
  chart.draw(google.visualization.arrayToDataTable(data), options);
}

function drawBarChart(data, options, element){
  var chart = new google.visualization.BarChart(element);
  chart.draw(google.visualization.arrayToDataTable(data), options);
}

    
  
    function load() {
        
      var json = <c:out value = "${requestScope.data}" escapeXml="false" />;

      var arr = [['Название', 'Значение']];

      for (var i = 0; i < json.length; i++){
        arr.push([json[i].title, json[i].value]);
    }
  

      var data = [
         ['Элемент', 'Значение'],
         ['Элемент 1', 10],
         ['Элемент 1', 200],
         ['Элемент 1', 700],
         ['Элемент 1', 40]
      ];

        var options2 = {
        title: "Диаграмма ",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };

      var options = {
        title: 'Работающие/Не Работающие'
      };

      var element = document.getElementById('piechart');
      var element2 = document.getElementById('div2');

      google.charts.setOnLoadCallback(function(){return drawPieChart(arr, options, element);});
      google.charts.setOnLoadCallback(function(){return drawColumnChart(arr, options2, div2);});
      google.charts.setOnLoadCallback(function(){return drawBarChart(arr, options2, div3);});
    }
  </script>
</head>
<body onload="load()">
  <div id="piechart" style="width: 900px; height: 400px;"></div>
  <div id="div2" style="width: 900px; height: 400px;"></div>
  <div id="div3" style="width: 900px; height: 400px;"></div>
</body>
</html>
