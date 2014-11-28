<?php

class LoginController extends BaseController
{
	public function getIndex()
	{
		// http://github.com/codenitive/laravel-oneauth/issues/14
		$message = unserialize(serialize(Session::get('message')));

		return View::make('login')
			->with('message', $message);
	}

	public function postIndex()
	{
		$login = Input::get('login');
		$password = Input::get('password');
		$remember = Input::get('remember');

		if(Auth::attempt(array('login' => $login, 'password' => $password), $remember))
		{
			return Redirect::to('/')
				->with('message', new Message(trans('messages.auth_ok', array('name' => Auth::user()->login)), Message::Success));
		}
		return Redirect::to('/login')
			->withInput()
			->with('message', new Message(trans('messages.auth_fail'), Message::Danger));
	}

	public function getLogout()
	{
		$name = Auth::user()->login;
		Auth::logout();
		return Redirect::to('/')
			->withInput()
			->with('message', new Message(trans('messages.logout', array('name' => $name)), Message::Success));
	}
}
