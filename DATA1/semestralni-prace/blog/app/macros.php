<?php

/*
|--------------------------------------------------------------------------
| HTML Macros
|--------------------------------------------------------------------------
|
| Here is where you can register all of the html macros for an application.
|
*/

HTML::macro('message', function(Message $message)
{
	switch($message->type)
	{
		case Message::Success: { $type = 'success'; $heading = 'Success'; break; }
		case Message::Warning: { $type = 'warning'; $heading = 'Warning'; break; }
		case Message::Danger:  { $type = 'danger';  $heading = 'Problem occured'; break; }
		default: $type = 'info'; $heading = 'Info';
	}

	return '
		<div class="panel panel-'.$type.'">
			<div class="panel-heading">'.$heading.'</div>
			<div class="panel-body">
				<p>'.$message->text.'</p>
			</div>
		</div>
	';
});

HTML::macro('alert', function(Message $message)
{
	switch($message->type)
	{
		case Message::Success: { $type = 'success'; break; }
		case Message::Warning: { $type = 'warning'; break; }
		case Message::Danger:  { $type = 'danger'; break; }
		default: $type = 'info';
	}

	return
	'
		<div class="alert alert-'.$type.'" role="alert">
				<p>'.$message->text.'</p>
		</div>
	';
});
