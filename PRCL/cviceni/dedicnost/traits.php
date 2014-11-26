<?php

/*
 * Some library that I don't wanna change
 */

class Shape {}
class Circle extends Shape {}
class Square extends Shape {}


/*
 * My code
 * I want to implement storing-into-file functionality
 */

// http://php.net/manual/en/language.oop5.traits.php
trait Storing
{
	public function store()
	{
		$class = get_class($this);
		echo "I should store $class into file\n";
	}
}

class StorableSquare extends Square
{
	use Storing;
}


/*
 * Usage
 */

$shape = new StorableSquare();
$shape->store();
