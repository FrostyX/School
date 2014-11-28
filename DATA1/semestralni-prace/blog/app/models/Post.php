<?php

class Post extends ORMFactory
{
	protected static $class = 'Post';
	protected static $table = 'posts';


	public function user()
	{
		return User::where('id', '=', $this->user_id)->get(0);
	}

	public function comments()
	{
		return Comment::where('post_id', '=', $this->id)->get();
	}
}
