<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>My CD Collection</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Title</th>
                        <th>Artist</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="catalog/cd/title"/></td>
                        <td><xsl:value-of select="catalog/cd/artist"/></td>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="catalog/cd[2]/title"/></td>
                        <td><xsl:value-of select="catalog/cd[2]/artist"/></td>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="catalog/cd[last()]/title"/></td>
                        <td><xsl:value-of select="catalog/cd[last()]/artist"/></td>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="catalog/cd[last()-1]/title"/></td>
                        <td><xsl:value-of select="catalog/cd[last()-1]/artist"/></td>
                    </tr>
                </table>
                <xsl:for-each select="catalog/cd[price > 10]">
                    <p style="font-size: 15px;"><xsl:value-of select="title"/></p>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>