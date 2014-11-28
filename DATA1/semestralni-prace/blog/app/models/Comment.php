<?php

class Comment extends ORMFactory
{
	protected static $class = 'Comment';
	protected static $table = 'comments';

	public function user()
	{
		return User::where('id', '=', $this->user_id)->get(0);
	}
}
