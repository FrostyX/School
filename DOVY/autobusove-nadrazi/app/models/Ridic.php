<?php

class Ridic extends BaseModel
{
	protected static $table = 'ridic';

	protected static function setProperties($result, $od=NULL, $do=NULL)
	{
		$result->ujel = static::ujel($result, $od ?: '0000-00-00', $do ?: '9999-99-99');
		return $result;
	}

	private static function ujel($result, $od, $do)
	{
		$sql =
		"
			SELECT SUM(vzdalenost) AS `ujel`
			FROM `spoj` JOIN `jizda`
			ON `spoj`.`linka_id`=`jizda`.`linka_id`
			WHERE `ridic_id`=$result->id
			AND `datum`>='$od' AND `datum`<='$do'
		";
		return DB::select($sql)[0]->ujel ?: 0;
	}

	public static function all($od=NULL, $do=NULL)
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table`";
		$results = DB::select($sql);
		foreach($results as $result)
		{
			static::setProperties($result, $od, $do);
		}
		return $results;
	}
}
