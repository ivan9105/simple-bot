<html>
<body>

<h1>Bookstore</h1>

<ul>
{
for $x in doc("FILE")/books/book
order by $x/title
return <li>{data($x/title)}. Category: {data($x/@category)}</li>
}
</ul>

</body>
</html>