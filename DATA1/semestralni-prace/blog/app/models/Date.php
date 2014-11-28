<?php

class Date
{
	public static function format($date, $format='d. m. Y H:i')
	{
		return date($format, strtotime($date));
	}
}
