<?php

class BaseModel
{
	protected static $table = NULL;

	public static function find($id)
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table` WHERE `id`=$id";
		$results = DB::select($sql);
		return static::setProperties($results[0]);
	}

	protected static function setProperties($obj)
	{
		return $obj;
	}
}
