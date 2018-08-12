<ul>
{
for $x in doc("FILE")/books/book/title
order by $x
return <li>{$x}</li>
}
</ul>