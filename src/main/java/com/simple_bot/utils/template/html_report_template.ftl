<html>
<head>
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${prismCss}" rel="stylesheet" />
    <script src="${prismJs}"/>
    <title>Html Report</title>
    <script>
        function toggleElement(elementId) {
            var div = document.getElementById(elementId)
            if (div.style.display !== 'none') {
                div.style.display = 'none';
            } else {
                div.style.display = 'block';
            }
        }
    </script>
</head>
<body>
    <h2>Steps</h2>
    <#list steps as step>
        ${step_index + 1}) <b>step.request</b><br/>
        <b>Request:</b>
        <div>
            <b>Cookie:</b><br/>
            <div>${cookie}</div><br/>
            <b>Header:</b><br/>
            <div>${header}</div><br/>
            <#if params?size != 0>
                <#list params as param>
                    ${param_index + 1})
                </#list>
            </#if>
        </div>
        <b>Response:</b>
        <div>
            <b>Http status:</b><br/>
            <div>${status}</div><br/>
            <b>Header:</b><br/>
            <div>${header}</div><br/>
            <b>Cookie:</b><br/>
            <div>${cookie}</div><br/>
            <b>Content:</b><br/>
            <div>${content}</div><br/>
        </div><br/>
    </#list>
</body>
</html>