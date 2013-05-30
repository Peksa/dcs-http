
local default_output_file = nil
net = {}

function LuaExportStart()
-- Works once just before mission start.
-- Make initializations of your files or connections here.
-- For example:
-- 1) File
   default_output_file = io.open("c:/tmp/Export.log", "w")
-- 2) Socket
	package.path  = package.path..";.\\LuaSocket\\?.lua" .. ";.\\Scripts\\?.lua"
	package.cpath = package.cpath..";.\\LuaSocket\\?.dll"
	socket = require("socket")
	net = require("net.main")
	host = host or "localhost"
	port = port or 9595
	c = socket.try(socket.connect(host, port)) -- connect to the listener socket
	c:setoption("tcp-nodelay",true) -- set immediate transmission mode

	local version = LoGetVersionInfo() --request current version info (as it showed by Windows Explorer fo DCS.exe properties)
	if version and default_output_file then
	    
		default_output_file:write("ProductName: "..version.ProductName..'\n')
		default_output_file:write(string.format("FileVersion: %d.%d.%d.%d\n",
												version.FileVersion[1],
												version.FileVersion[2],
												version.FileVersion[3],
												version.FileVersion[4]))
		default_output_file:write(	string.format("ProductVersion: %d.%d.%d.%d\n",
												version.ProductVersion[1],
												version.ProductVersion[2],
												version.ProductVersion[3],  -- head  revision (Continuously growth)
												version.ProductVersion[4])) -- build number   (Continuously growth)	
	end

end

function LuaExportBeforeNextFrame()

end

function LuaExportAfterNextFrame()

end

function LuaExportStop()
-- Works once just after mission stop.
-- Close files and/or connections here.
-- 1) File
   if default_output_file then
	  default_output_file:close()
	  default_output_file = nil
   end
-- 2) Socket
socket.try(c:send("quit")) -- to close the listener socket
c:close()
end

function LuaExportActivityNextEvent(t)
	local tNext = t

	local o = LoGetWorldObjects()
 	if default_output_file then
		--default_output_file:write(string.format("%s %s %s\n", net.get_name(0), net.get_name(1), net.get_name(2)))
		for k,v in pairs(o) do
			if v.GroupName ~= nil then

				--default_output_file:write(string.format("t = %.2f, ID = %d, name = %s, country = %s(%s), LatLongAlt = (%f, %f, %f), heading = %f, UnitName = %s, GroupName = %s\n",
				--	t, k, v.Name, v.Country, v.Coalition, v.LatLongAlt.Lat, v.LatLongAlt.Long, v.LatLongAlt.Alt, v.Heading, v.UnitName, v.GroupName))
				socket.try(c:send(string.format("O\t%.2f\t%d\t%s\t%f\t%f\t%f\t%f\t%s\t%s\t%s\n", t, k, v.Name, v.LatLongAlt.Lat, v.LatLongAlt.Long, v.LatLongAlt.Alt, v.Heading, v.Coalition, v.UnitName, v.GroupName)))
			end
		end
	end

	tNext = tNext + 0.5

	return tNext
end

