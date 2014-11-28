<?php

class User extends ORMFactory
{
	protected static $class = 'User';
	protected static $table = 'users';

	public function posts()
	{
		return Post::where('user_id', '=', $this->id)->get();
	}
}
