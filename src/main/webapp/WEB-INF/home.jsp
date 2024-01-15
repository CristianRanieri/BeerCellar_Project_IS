<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- Media Query -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_generic.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css_top_bar.css" type="text/css"><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script><!-- questa inclusione va messa ovunque viene fatta la @include del contenitore -->
    </head>

    <body>

    <jsp:include page="header.jsp">
        <jsp:param name="header" value=""/>
    </jsp:include>



    <jsp:include page="footer.jsp">
        <jsp:param name="footer" value=""/>
    </jsp:include>
    </body>
</html>
