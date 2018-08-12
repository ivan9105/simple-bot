for $x in doc("FILE")/books/book
where $x/price>30
order by $x/title descending
return $x/title