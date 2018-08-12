for $x in doc("FILE")/books/book
return if ($x/@category="XML")
then <xml>{data($x/title)}</xml>
else <other>{data($x/title)}</other>