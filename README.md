# partnerlog

Minimal Android app (v0) with one screen containing a title and an Exit button.

CI workflows:
- PR workflow builds and uploads a debug APK.
- Release workflow builds and uploads a debug APK and attaches it to the GitHub release.

APK signing uses a consistent debug keystore restored from CI secrets so builds stay upgrade-compatible.
This key is for debug APKs only and must not be reused for production signing. Configure these repository secrets:
- `PARTNERLOG_DEBUG_KEYSTORE_BASE64` (base64 of the debug keystore file)
- `PARTNERLOG_DEBUG_KEYSTORE_PASSWORD`
- `PARTNERLOG_DEBUG_KEY_PASSWORD`
