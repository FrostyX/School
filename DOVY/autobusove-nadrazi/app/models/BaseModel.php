<?php

class BaseModel
{
	protected static $table = NULL;

	public static function find($id)
	{
		if(!$id) return NULL;

		$table = static::$table;
		$sql = "SELECT * FROM `$table` WHERE `id`=$id";
		$results = DB::select($sql);
		return $results[0];
	}

	public static function nextID()
	{
		$table = static::$table;
		$sql = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = '$table'";
		return DB::select($sql)[0]->auto_increment;
	}
}
