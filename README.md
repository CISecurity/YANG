# YANG
YANG data models for use in IETF/SACM

## IETF 102 Hackathon
1. Define a generic data model for use in the collection of system characteristics from an endpoint
2. Define specific data model(s) to represent the current configuration of an endpoint (the system characteristics themselves)
3. Define a generic data model for use in the evaluation of system characteristics against an expected state

## Structure
- sacm-core.yang
Analagous to OVAL Common, this module will define constructs common to all SACM-based collection and system characteristics.
- sacm-collection-core.yang
Analagous to OVAL Definitions, this module defines constructs common to the function of collecting information from an endpoint.
- sacm-system-characteristics-core.yang
Analagous to OVAL System Characteristics, this module defines constructs common to information retrieved from an endpoint.
- sacm-microsoft-windowss-collection.yang
Information on how to collect information from Microsoft Windows endpoints.
- sacm-microsoft-windows-system-characteristics.yang
- Information collected from Microsoft Windows endpoints.
