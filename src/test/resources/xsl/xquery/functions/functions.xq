declare function local:minPrice($p as xs:decimal?,$d as xs:decimal?)
as xs:decimal?
{
let $disc := ($p * $d) div 100
return ($p - $disc)
};

<html>
<body>

<h1>Bookstore</h1>

<ul>
{
for $x in doc("FILE")/books/book
order by $x/title
return <li>{data($x/title)}. Category: {data($x/@category)}.<p>Discount Price: {local:minPrice($x/price,10)}</p></li>
}
</ul>
</body>
</html>