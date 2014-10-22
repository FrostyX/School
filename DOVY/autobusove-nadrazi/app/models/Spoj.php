<?php

class Spoj
{
	private static $SELECT = "SELECT * FROM `spoj`";

	public static function find($spoj_id)
	{
		$sql = self::$SELECT." WHERE `id`=$spoj_id";
		$spoje = DB::select($sql);
		return $spoje[0];
	}

	public static function between($odkud_id, $kam_id)
	{
		$sql = self::$SELECT." WHERE `odkud`=$odkud_id AND `kam`=$kam_id";
		$spoje = DB::select($sql);
		return $spoje;
	}
}
