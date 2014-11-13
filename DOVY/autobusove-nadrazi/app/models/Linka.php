<?php

class Linka extends BaseModel
{
	protected static $table = 'linka';

	protected static function setProperties($result, $datum)
	{
		$zastavky = static::zastavky($result);
		$result->odkud = $zastavky[0];
		$result->kam = $zastavky[count($zastavky)-1];
		$result->trzba = static::trzba($result, $datum);
		return $result;
	}

	public static function all($datum=NULL)
	{
		$table = static::$table;
		$sql = "SELECT * FROM `$table`";

		$results = DB::select($sql);
		foreach($results as $result)
		{
			static::setProperties($result, $datum);
		}
		return $results;
	}

	public static function trzba($result, $datum)
	{
		$sql =
		"
			SELECT SUM(trzba) AS trzba
			FROM `jizda`
			WHERE `linka_id`=$result->id AND `datum`='$datum'
		";
		return DB::select($sql)[0]->trzba ?: 0;
	}

	public static function zastavky($result)
	{
		$sql =
		"
			SELECT `zastavka`.*, `zastavka_linka`.`poradi`
			FROM `zastavka` JOIN `zastavka_linka`
			ON `zastavka`.`id`=`zastavka_linka`.`zastavka_id`
			WHERE `linka_id`=$result->id
			ORDER BY `poradi`
		";
		return DB::select($sql);
	}
}
