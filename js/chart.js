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

//data - многомерный массив, в которым находятся сами данные для построения графика. Минимум 2 колонки - название элемента графика и числовое представление
//options - как я понял, структура типа стиля сss, которая применяется к самому графику
//element - объект контейнера, который будет содержать график. Так же можно просто прописывать id контейнера

//how to use
//google.charts.setOnLoadCallback(function(){return drawColumnChart(data, options, element);});
