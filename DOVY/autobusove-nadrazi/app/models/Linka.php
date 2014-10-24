<?php

class Linka extends BaseModel
{
	protected static $table = 'linka';

	protected static function setProperties($result)
	{
		$result->trzba = 400;
		return $result;
	}

	public static function all()
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table`";
		$results = DB::select($sql);
		foreach($results as $result)
		{
			static::setProperties($result);
		}
		return $results;
	}
}
