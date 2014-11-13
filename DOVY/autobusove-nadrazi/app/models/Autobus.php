<?php

class Autobus extends BaseModel
{
	protected static $table = 'autobus';

	protected static function setProperties($result, $od=NULL, $do=NULL)
	{
		$result->ujel = static::ujel($result, $od ?: '0000-00-00', $do ?: '9999-99-99');
		$result->prumernaSpotreba = (($result->ujel / self::dnuVeSluzbe($result)) / 100) * $result->spotreba;
		return $result;
	}

	private static function ujel($result, $od, $do)
	{
		$sql =
		"
			SELECT SUM(vzdalenost) AS `ujel`
			FROM `spoj` JOIN `jizda`
			ON `spoj`.`linka_id`=`jizda`.`linka_id`
			WHERE `autobus_id`=$result->id
			AND `datum`>='$od' AND `datum`<='$do'
		";
		return DB::select($sql)[0]->ujel ?: 0;
	}

	public static function all($sort='id', $od, $do)
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table`";
		$results = DB::select($sql);
		foreach($results as $result)
		{
			static::setProperties($result);
		}
		uasort($results, function($a, $b) use (&$sort)
		{
			return strcmp($a->$sort, $b->$sort);
		});
		return $results;
	}

	public static function dnuVeSluzbe($result)
	{
		$sql = "SELECT DISTINCT COUNT(datum) as dnu from jizda WHERE autobus_id=$result->id";
		return DB::select($sql)[0]->dnu;
	}
}
