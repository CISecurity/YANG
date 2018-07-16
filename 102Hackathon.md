# IETF 102 Hackathon

Why were we here?  We were trying to determine if we can model the *collection* of configuration information.  In our (admittedly very limited) experience, the YANG model expressions have been great at detailing the configuration and characteristics of endpoints, but we wanted a way to describe *what to collect* from that endpoint, and indirectly *how* to collect it.

## Goals
1. Define a simple data model for use in the collection of system characteristics from an endpoint.
2. Define specific data model(s) to represent the current configuration of an endpoint (the system characteristics themselves)
3. Define a generic data model for use in the evaluation of system characteristics against an expected state

## Accomplishments
1. We were able to create and compile a simple YANG model that can be used to express the desired information to be collected from a target endpoint.
2. We created some code to perform the collection of system characteristics based on the information in the "collection" data model instance.
3. We began the creation of some more complex and specific YANG models to express system characteristics that could be collected from a target system.  We focused on Windows systems for this hackathon, but other operating systems would follow closely.

## Next Steps
1. Expand the code used for the collection of system information.  The very small, specific use-case that was coded was towards the end of the hackathon and very simple.
2. Continue to develop more models for more operating systems.
3. Engage more people on the list to develop more models and more code.
4. Research more into if/how NETCONF/RESTCONF accomplishes the task of collection for specific endpoint attributes.
