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
    <script defer type="text/javascript" src="js/chart.js"></script>
    <script type="text/javascript">
        // google.charts.load('current', {'packages':['corechart']});
        // google.charts.setOnLoadCallback(drawChart);
        // function drawChart() {

        //     var data = google.visualization.arrayToDataTable([
        //         ['Task', 'Hours per Day'],
        //         ['Work',     11],
        //         ['Eat',      2],
        //         ['Commute',  2]
        //     ]);

        //     var options = {
        //         title: 'My Daily Activities'
        //     };

        //     var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        //     chart.draw(data, options);
        // }

        function load(){
         var arr = [
         ['Название', 'Количество'],
         ['Работают',     3236],
         ['Не работают',  2556]
         ];

         var options = {
          title: 'Работающие/Не Работающие'
        };
        var element = document.getElementById('piechart');
        google.charts.setOnLoadCallback(function(){return drawPieChart(arr, options, element);});
      }
    </script>
</head>
<body onload="load()">
<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
</html>
