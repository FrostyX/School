<?php

class SelectQuery
{
	protected $sql = "";
	protected $table;
	protected $class;

	public function __construct($sql, $table, $class)
	{
		$this->sql = $sql;
		$this->table = $table;
		$this->class = $class;
	}

	public function get($row=NULL)
	{
		$class = $this->class;
		$objects = [];
		foreach(DB::select($this->sql) as $object)
		{
			$objects[] = $class::create($object);
		}

		if($row === NULL)
			return $objects;
		return $objects[$row];
	}

	public function aggregate()
	{
		return DB::select($this->sql)[0]->value;
	}

	public function where($column, $operator, $value)
	{
		$this->sql .= " WHERE $column $operator '$value'";
		return $this;
	}
}
