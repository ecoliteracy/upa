
<!DOCTYPE html>
<script type='text/javascript' src='resources/js/jquery.js'></script>
<link href='resources/css/calendar.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="resources/js/app.js"></script>
<div align='left'>
	Select Date: <input type='text' id='sel' onclick='dispCal()' size=10
		readonly='readonly' /> <img src='resources/img/calendar.png'
		onclick='dispCal()' style='cursor: pointer; vertical-align: middle;' />
	<table class='calendar' id='calendar' border=0 cellpadding=0
		cellspacing=0>
		<tr class='monthdisp'>
			<td class='navigate' align='left'><img
				src='resources/img/previous.png' onclick='return prev()' /></td>
			<td align='center' id='month'></td>
			<td class='navigate' align='right'><img
				src='resources/img/next.png' onclick='return next()' /></td>
		</tr>
		<tr>
			<td colspan=3>
				<table id='dispDays' border=0 cellpadding=4 cellspacing=4>
				</table>
			</td>
		</tr>
	</table>
</div>