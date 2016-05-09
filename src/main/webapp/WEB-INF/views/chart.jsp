<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chart</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <!-- <script src="diploma-stuff/resources/js/chart.js" type="text/javascript"></script> -->
    <spring:url value="/resources/js/chart.js" var="chart" />
    <script src="${chart}"></script>

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
