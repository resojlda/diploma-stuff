<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
  <title>Chart</title>
    <spring:url value="/resources/js/percentchart/dist/progressbar.js" var="percent" />
    <spring:url value="/resources/css/style.css" var="css" />
    <link href="${css}" rel="stylesheet" />
    <script src="${percent}"></script>
  <script>
      
    function load() {
        
     var bar = new ProgressBar.Circle(container, {
  color: '#800000',
  // This has to be the same size as the maximum width to
  // prevent clipping
  strokeWidth: 4,
  trailWidth: 3,
  easing: 'easeInOut',
  duration: 1400,
  text: {
    autoStyleContainer: false
  },
  from: { color: '#aaa', width: 1 },
  to: { color: '##0000FF', width: 4 },
  // Set default step function for all animate calls
  step: function(state, circle) {
    circle.path.setAttribute('stroke', state.color);
    circle.path.setAttribute('stroke-width', state.width);

    var value = Math.round(circle.value() * 100);
    if (value === 0) {
      circle.setText('');
    } else {
      circle.setText(value + '%');
    }

  }
});
bar.text.style.fontFamily = 'Arial';
bar.text.style.fontSize = '3rem';

bar.animate(0.775);  // Number from 0.0 to 1.0
    }
  </script>
</head>
<body onload="load()">
<div id="container"></div>
</body>
</html>
