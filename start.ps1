[string] $IPAddress = "192.168.1.17"
[string] $cmd = "adb -s " + $IPAddress + ":5555 shell pm path com.example.resetmara"
[string] $str = Invoke-Expression $cmd
$str.Substring("package:".Length) | Out-Null
$str = '"CLASSPATH=' + $str + ' /system/bin/app_process /system/bin com.example.resetmara.InputHost"'
# ↓永続化の場合
#$str = '"CLASSPATH=' + $str + ' /system/bin/app_process /system/bin com.example.resetmara.InputHost >/dev/null 2>&1 </dev/null &"'
$cmd = 'adb -s ' + $IPAddress + ' shell ' + $str
Invoke-Expression $cmd
