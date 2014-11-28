<?php

class Message
{
	const Success = 0;
	const Info = 1;
	const Warning = 2;
	const Danger = 3;

	public $text;
	public $type;

	public function __construct($text, $type=NULL)
	{
		$this->text = $text;
		$this->type = $type;
	}
}
