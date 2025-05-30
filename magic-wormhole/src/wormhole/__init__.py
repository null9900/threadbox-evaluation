from ._rlcompleter import input_with_completion
from .wormhole import create
from ._status import WormholeStatus, DilationStatus  # export as public API
from threadbox import sandbox_function, sandbox_ps, permissions

from . import _version
__version__ = _version.get_versions()['version']

sandbox_ps()
__all__ = [
    "__version__",
    "create", "input_with_completion",
    "WormholeStatus",
    "DilationStatus",
]
