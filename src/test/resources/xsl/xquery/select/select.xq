for $x in doc("FILE")/books/book
where $x/price>30
return $x/title