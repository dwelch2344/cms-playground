<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here 
		</script>
  	</jsp:attribute>  
	<jsp:body>
		<h1>Hello World</h1>
		<p>This is just an example page!</p>
		<form action="upload" method="post" enctype="multipart/form-data">
        	<input type="file" name="file"/> <br/>
        	<input name="classname"/>
        	<input type="submit" value="Upload"/>
        </form>
	</jsp:body>
</tags:template>