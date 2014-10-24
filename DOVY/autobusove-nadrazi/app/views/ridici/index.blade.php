@extends('layouts.main')
@section('content')
	<h1>Řidiči</h1>
	<div class="panel panel-default">
		<div class="panel-heading">Zobrazen přehled od <b>{{ $od }}</b> do <b>{{ $do }}</b></div>
		<table class="table table-striped">
		<thead>
			<tr>
				<th>Jméno</th>
				<th>Příjmení</th>
				<th>Ujel</th>
			</tr>
		</thead>
		<tbody>
			@foreach($ridici as $ridic)
			<tr>
				<td>{{ $ridic->jmeno }}</td>
				<td>{{ $ridic->prijmeni }}</td>
				<td>{{ $ridic->ujel }} km</td>
			</tr>
			@endforeach
		</tbody>
	</div>
@stop
