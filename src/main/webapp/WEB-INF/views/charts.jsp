
<%--
  Created by IntelliJ IDEA.
  User: Den
  Date: 23.03.2016
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chart</title>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!--   <script defer type="text/javascript" src="chart.js"></script> -->
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

      var arr = [
      ['Название', 'Количество'],
      ['Работают',     3236],
      ['Не работают',  2556]
      ];

      var data = [
         ['Элемент', 'Значение', { role: 'style' }, { role: 'annotation' } ],
         ['Элемент 1', 10, 'yellow', 'Э1' ],
         ['Элемент 1', 200, 'green', 'Э2' ],
         ['Элемент 1', 700, 'blue', 'Э3' ],
         ['Элемент 1', 40, 'red', 'Э4' ]
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

      google.charts.setOnLoadCallback(function(){return drawPieChart(data, options, element);});
      google.charts.setOnLoadCallback(function(){return drawColumnChart(data, options2, div2);});
      google.charts.setOnLoadCallback(function(){return drawBarChart(data, options2, div3);});
    }
  </script>
</head>
<body onload="load()">
  <div id="piechart" style="width: 900px; height: 400px;"></div>
  <div id="div2" style="width: 900px; height: 400px;"></div>
  <div id="div3" style="width: 900px; height: 400px;"></div>
</body>
</html>
