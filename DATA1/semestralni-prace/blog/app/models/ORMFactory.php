<?php

class ORMFactory
{
	/*
	|--------------------------------------------------------------------------
	| Factory
	|--------------------------------------------------------------------------
	|
	| ...
	|
	*/

	public static function create($stdObj)
	{
		$obj = new static::$class();
		foreach($stdObj as $key => $value)
			$obj->$key = $value;
		return $obj;
	}

	protected static function SelectQuery($sql='')
	{
		return new SelectQuery($sql, static::$table, static::$class);
	}


	/*
	|--------------------------------------------------------------------------
	| Properties
	|--------------------------------------------------------------------------
	|
	| ...
	|
	*/

	public function __get($property)
	{
		if(property_exists($this, $property))
			return $this->$property;
		return NULL;
	}

	/*
	|--------------------------------------------------------------------------
	| Selecting
	|--------------------------------------------------------------------------
	|
	| ...
	|
	*/

	public static function all()
	{
		$table = static::$table;
		$class = static::$class;

		return static::SelectQuery("SELECT * FROM $table")->get();
	}

	public static function where($column, $operator, $value)
	{
		$table = static::$table;
		$class = static::$class;

		return static::SelectQuery("SELECT * FROM $table WHERE $column $operator '$value'");
	}


	/*
	|--------------------------------------------------------------------------
	| Aggregates
	|--------------------------------------------------------------------------
	|
	| ...
	|
	*/

	public static function max($column)
	{
		$table = static::$table;
		$query = static::SelectQuery("SELECT MAX($column) AS value FROM $table");
		return $query;
	}


	/*
	|--------------------------------------------------------------------------
	| Modification
	|--------------------------------------------------------------------------
	|
	| ...
	|
	*/

	public function save()
	{
		return (property_exists($this, 'id') && $this->id) ? $this->update() : $this->insert();
	}

	protected function update()
	{
		$table = static::$table;

		$id = $this->id;
		unset($this->id);
		$properties = get_object_vars($this);
		$keys = array_keys(get_object_vars($this));
		$values = array_values($properties);

		$sql = "UPDATE $table SET ".implode('=?, ', $keys)."=? WHERE id=$id";
		return DB::update($sql, $values);
	}

	protected function insert()
	{
		$table = static::$table;

		unset($this->id);
		$properties = get_object_vars($this);
		$keys = array_keys(get_object_vars($this));
		$values = array_values($properties);

		$sqlAttributes = implode(', ', $keys);
		$sqlValues = implode(', ', array_fill(0, count($properties), '?'));

		return DB::insert("INSERT INTO $table ($sqlAttributes) VALUES ($sqlValues)", $values);
	}

}
