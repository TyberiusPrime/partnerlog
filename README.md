# PartnerLog

Minimal Android app (v0) with one screen containing a title and an Exit button.

CI workflows:
- PR workflow builds and uploads a debug APK.
- Release workflow builds and uploads a debug APK and attaches it to the GitHub release.

APK signing uses a consistent debug keystore restored from CI secrets so builds stay upgrade-compatible.
This key is for debug APKs only and must not be reused for production signing. Configure these repository secrets:
- `PARTNERLOG_DEBUG_KEYSTORE_BASE64` (base64 of the debug keystore file)
- `PARTNERLOG_DEBUG_KEYSTORE_PASSWORD`
- `PARTNERLOG_DEBUG_KEY_PASSWORD`

For local builds, create or copy the same debug keystore to `signing/debug.keystore` at the project root if you want to use the same signing identity as CI. Without it, local debug builds fall back to the standard Android debug keystore.

Note: PR builds can run without these secrets and will fall back to the standard Android debug keystore, but only repository-owned runs with the configured secrets will produce the stable signing identity used for upgrade-compatible APKs.
