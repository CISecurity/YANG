<?xml version="1.0" encoding="utf-8"?>
<sch:schema xmlns:sch="http://purl.oclc.org/dsdl/schematron" queryBinding="exslt"><sch:ns uri="http://exslt.org/dynamic" prefix="dyn"/><sch:ns uri="http://cisecurity.org/yang/sacm-collection" prefix="sacm-collection"/><sch:ns uri="urn:ietf:params:xml:ns:netconf:base:1.0" prefix="nc"/><sch:let name="root" value="/nc:data"/><sch:pattern abstract="true" id="sacm-collection___set"><sch:rule context="$start/$pref:object1"><sch:report test="not($root/$pref:collections/$pref:collection/$pref:collection-id=.)">Leaf "/nc:data/$pref:collections/$pref:collection/$pref:collection-id" does not exist for leafref value "<sch:value-of select="."/>"</sch:report></sch:rule><sch:rule context="$start/$pref:object2"><sch:report test="not($root/$pref:collections/$pref:collection/$pref:collection-id=.)">Leaf "/nc:data/$pref:collections/$pref:collection/$pref:collection-id" does not exist for leafref value "<sch:value-of select="."/>"</sch:report></sch:rule></sch:pattern><sch:pattern abstract="true" id="sacm-collection___filter"><sch:rule context="$start/$pref:filter-fields/$pref:filter-field"><sch:report test="preceding-sibling::$pref:filter-field[$pref:name=current()/$pref:name]">Duplicate key "name"</sch:report></sch:rule></sch:pattern><sch:pattern abstract="true" id="sacm-collection___collection-fields"><sch:rule context="$start/$pref:collection-field"><sch:report test="preceding-sibling::$pref:collection-field[$pref:name=current()/$pref:name]">Duplicate key "name"</sch:report></sch:rule></sch:pattern><sch:pattern id="sacm-collection"><sch:rule context="/nc:data/sacm-collection:collections/sacm-collection:collection"><sch:report test="preceding-sibling::sacm-collection:collection[sacm-collection:collection-id=current()/sacm-collection:collection-id]">Duplicate key "sacm-collection:collection-id"</sch:report></sch:rule></sch:pattern><sch:pattern id="idm45225860416592" is-a="sacm-collection___collection-fields"><sch:param name="start" value="/nc:data/sacm-collection:collections/sacm-collection:collection/sacm-collection:collection-fields"/><sch:param name="pref" value="sacm-collection"/></sch:pattern><sch:pattern id="idm45225860415728" is-a="sacm-collection___set"><sch:param name="start" value="/nc:data/sacm-collection:collections/sacm-collection:collection/sacm-collection:set"/><sch:param name="pref" value="sacm-collection"/></sch:pattern><sch:pattern id="idm45225860414864" is-a="sacm-collection___filter"><sch:param name="start" value="/nc:data/sacm-collection:collections/sacm-collection:collection/sacm-collection:filter"/><sch:param name="pref" value="sacm-collection"/></sch:pattern></sch:schema>
