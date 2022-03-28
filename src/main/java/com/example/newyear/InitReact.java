package com.example.newyear;

public class InitReact {

    private StringBuilder sb;

    public InitReact() {
        sb = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
                .append("<html>")
                .append("<head>")
                .append("<title>New Resolution</title>")
                .append("<meta charset=\"UTF-8\">")
                .append("<link rel=\"stylesheet\", type=\"text/css\", href=\"/dist/bundle.css\")>")
                .append("<link rel=\"icon\" href=\"/assets/logo.png\"")
                .append("</head>")
                .append("<body>")
                .append("<div id=\"reactEntryPoint\" class=\"background\">")
                .append("<script src=\"/dist/bundle.js\">")
                .append("</script>")
                .append("</div>")
                .append("</body>")
                .append("</html>");
    }

    public String serialize() {
        return sb.toString();
    }
}
